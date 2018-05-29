package app.regime.com.ui.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.pixplicity.easyprefs.library.Prefs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import app.regime.com.R;
import app.regime.com.model.Category;
import app.regime.com.model.Deal;
import app.regime.com.model.Item;
import app.regime.com.model.Title;
import app.regime.com.ui.FragmentContact;
import app.regime.com.ui.adapter.ExpandableListAdapter;
import app.regime.com.ui.adapter.HorizontalAdapter;
import app.regime.com.ui.adapter.TitleHorizontalAdapter;
import app.regime.com.utills.CommonUtils;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

import static app.regime.com.utills.CommonUtils.isMultipleSelect_onFullDay;
import static app.regime.com.utills.CommonUtils.multipleSelectCategory;

@SuppressLint("ValidFragment")
public class FullDayMealsFragment extends Fragment implements ExpandableListAdapter.UpdateList, TitleHorizontalAdapter.CallbackTitle {
    LinearLayout BreakFast, MainCourses, Salads, Soups, Desserts;
    View BreakFastView, MainCoursesView, SaladsView, SoupsView, DessertsView;
    Button btn_select_meal;
    FragmentContact fragmentContact;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;

    RecyclerView horizontal_recycler_view;
    TitleHorizontalAdapter horizontalAdapter;

    ArrayList<Category> selectCategory = new ArrayList<>();
    ArrayList<Category>[] daysselectCategory;
    ArrayList<Category>[] daysCategory;
    Calendar start_Date, select_Date;

    int size = 0;

    ArrayList<Title> titles = new ArrayList<>();
    String dummyDate = "2018-05-20";
    int intNoOFDays;
    boolean[] selectmeal;
    //   Deal deal = new Deal();
    boolean[] menuSelectDayCheck;
    int check_day_index = 0;
    String curentDate = "2018-05-20";
    int load = 0;
    boolean isFullDayMeal = true;
    TextView tv_title,tv_dayName;

    ArrayList<Category> categories = new ArrayList<>();

    public FullDayMealsFragment(FragmentContact fragmentContact) {
        this.fragmentContact = fragmentContact;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.full_day_meal_fragment, container, false);
        Bundle bundle = this.getArguments();

        String strNoOfDays = bundle.getString("no_of_days");
        String mealType = bundle.getString("type_of_meal");
        if (mealType.equals("Lunch Only")) {
            isFullDayMeal = false;
        }
        intNoOFDays = Integer.parseInt(strNoOfDays);
        expListView = (ExpandableListView) view.findViewById(R.id.lvExp);
        tv_title=(TextView)view.findViewById(R.id.tv_title_meal);
        tv_dayName=(TextView)view.findViewById(R.id.tv_day_name);
        daysselectCategory = new ArrayList[intNoOFDays];
        daysCategory = new ArrayList[intNoOFDays];
        btn_select_meal = (Button) view.findViewById(R.id.btn_select_meal);
        horizontal_recycler_view = (RecyclerView) view.findViewById(R.id.title_recyleview);


        horizontalAdapter = new TitleHorizontalAdapter(titles, getActivity(), this);

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManager);
        horizontal_recycler_view.setAdapter(horizontalAdapter);
        tv_title.setText(mealType);

        menuSelectDayCheck = new boolean[intNoOFDays];
        for (int i = 0; i < intNoOFDays; i++) {
            menuSelectDayCheck[i] = false;
        }

        int intStartDate = Prefs.getInt("date", 0);
        int day = Prefs.getInt("day", 0);
        int month = Prefs.getInt("month", 0);
        int year = Prefs.getInt("year", 0);
        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        start_Date = startDate;

        //  Toast.makeText(getActivity(), "" + Calendar.DATE, Toast.LENGTH_SHORT).show();
        startDate.add(Calendar.DATE, 3);

        getMeals(getDateString(startDate));

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.DATE, intNoOFDays + 2);

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(view, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();


        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                //do something
                if (AllCheck()) {
                    daysselectCategory[check_day_index] = selectCategory;

                    if (!menuSelectDayCheck[check_day_index]) {
                        menuSelectDayCheck[check_day_index] = true;
                        size++;
                    }

                } else {
                    Toast.makeText(getActivity(), "You have not select all category from " + curentDate, Toast.LENGTH_SHORT).show();

                }
                if (load != intNoOFDays) {
                    getMeals(getDateString(date));
                } else {
                    getDateString(date);
                    SetAdapter_list(check_day_index);
                }
                //      Toast.makeText(getContext(), ""+position+" "+getDateString(date), Toast.LENGTH_SHORT).show();

            }
        });


        //  GetMeals();

        btn_select_meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AllCheck()) {
                    daysselectCategory[check_day_index] = selectCategory;
                    if (!menuSelectDayCheck[check_day_index]) {
                        menuSelectDayCheck[check_day_index] = true;
                        size++;
                    }

                    if (size == intNoOFDays) {
                        Bundle bundle = getArguments();
                        bundle.putSerializable("Cat_List", daysselectCategory);
                        fragmentContact.ChangeFragment("FullMealDetailFragment", bundle);
                    } else {
                        Toast.makeText(getActivity(), "Please select all days menu!", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    String msg = "";
                    if (isFullDayMeal) {

                        msg = "Please select 2 item from Salad, MainCourse and Soup";
                    } else
                        msg = "Please select all category!";
                    Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();

                }

            }
        });

        return view;
    }

   /* public void updateCheckIndex() {
        //check_day_index=check_day_index+(current_day-last_day);
        if (last_day < current_day) {
            check_day_index++;
        } else {
            check_day_index--;
        }
    }*/

    public void getMeals(final String date) {
        if (!isContainData()) {
            load++;
     /*   Intent intent1 = new Intent(SignInActivity.this, MissOutActivity.class);
        startActivity(intent1);*/
            final ProgressDialog progressDialog = CommonUtils.showLoadingDialog(getActivity());
            progressDialog.show();

            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", 0);
            RequestParams params = new RequestParams();
            AsyncHttpClient client = new AsyncHttpClient();
            client.addHeader("x-access-key", "UUAU-13T6-10R9-L6R5");
//        client.addHeader("x-access-token", sharedPreferences.getString("token", ""));
            client.addHeader("x-access-token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjo1MjEsIm5hbWUiOiJhYmJhcyIsImlhdCI6MTUxODU5ODkwNH0.Tc36x6e_DNgVSb9PnLyQuXYLjEpBWVgmuju0IXgcUoI");

            client.addHeader("Content-Type", "application/x-www-form-urlencoded");
            client.get("https://regim.herokuapp.com/api/profile/orderDeatils?currentDate=" + dummyDate, params, new JsonHttpResponseHandler() {
                @Override
                public void onStart() {
                    Log.e("response UpdateProfile", "start");
                }

                @Override
                public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    try {
                        progressDialog.dismiss();
                        JSONObject jsonObject = response;
                        int status = jsonObject.getInt("status");
                        //categories.clear();
                        categories = new ArrayList<>();

                        titles.clear();
                        selectCategory = new ArrayList<>();
                        if (status == 200) {
                            JSONArray message = jsonObject.getJSONArray("categories");

                            selectmeal = new boolean[message.length()];
                            for (int i = 0; i < message.length(); i++) {
                                selectmeal[i] = false;
                                JSONObject data = message.getJSONObject(i);
                                Category category = new Category();
                                category.setDate(date);
                                category.setCategoryName(data.getString("CategoryName"));
                                category.items = new ArrayList<>();
                                //   JSONArray items = data.getJSONArray("Items");
/*
                            for (int j=0;j<items.length();j++){
                                JSONObject temp = items.getJSONObject(i);
                                category.items.add(temp.getString("item"));
                            }*/
                                String str = data.getString("Items");
                                if (str != null && !str.contains("No Menu Avaliable")) {
                                    JSONArray itemsJson = new JSONArray(str);

                                    for (int j = 0; j < itemsJson.length(); j++) {

                                        JSONObject temp = itemsJson.getJSONObject(j);
                                        category.items.add(new Item(temp.getString("item" + (j + 1))));
                                    }
                                }
                                categories.add(category);

                                selectCategory.add(new Category(data.getString("CategoryName"), new ArrayList<Item>()));
                                selectCategory.get(i).items.add(null);
                                if (isFullDayMeal && isMultipleSelect_onFullDay(selectCategory.get(i).getCategoryName())) {
                                    selectCategory.get(i).items.add(null);
                                }
                                titles.add(new Title(data.getString("CategoryName")));
                            }
                            titles.get(0).setSelect(true);
                            daysCategory[check_day_index] = categories;
                            SetAdapter_list(check_day_index);
                            //    listAdapter.notifyDataSetChanged();
                            horizontalAdapter.notifyDataSetChanged();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("Exception", "" + e);
                    }
                }

                @Override
                public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONArray errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(), "Please try again", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onRetry(int retryNo) {
                }
            });
        } else {
            SetAdapter_list(check_day_index);

        }
    }

    @Override
    public void ClickChildView(final int groupPosition, final int childPosition, boolean isFull) {
        Boolean select = false;
        ArrayList<Category> categories = daysCategory[check_day_index];

       /* if (selectCategory.get(groupPosition).items.size() == 2) {
            for (Item item1 : selectCategory.get(groupPosition).items) {
                if (isFull && item1.isCheck1()) {
                    temp_item = item1;
                    select = true;
                } else if (!isFull && item1.isCheck2()) {
                    select = true;
                    temp_item = item1;
                }
            }
        }

        selectCategory.get(groupPosition).items.clear();
       */
        for (Item temp : categories.get(groupPosition).items) {
            if (!isFull) {
                if (temp.isCheck1()) {
                    /*if (!select) {
                        select = true;
                        selectCategory.get(groupPosition).items.add(temp);
                    } else*/
                    temp.setCheck1(false);


                }
            } else {
                if (temp.isCheck2()) {

                    temp.setCheck2(false);

                }
            }

        }

        if (!isFull) {
            categories.get(groupPosition).items.get(childPosition).setCheck1(true);
            Item item = categories.get(groupPosition).items.get(childPosition);
            selectCategory.get(groupPosition).items.set(0, item);

        } else {
            categories.get(groupPosition).items.get(childPosition).setCheck2(true);
            Item item = categories.get(groupPosition).items.get(childPosition);
            selectCategory.get(groupPosition).items.set(1, item);

        }

        //  selectCategory.get(groupPosition).items = new ArrayList<>();
        if (isFullDayMeal && isMultipleSelect_onFullDay(categories.get(groupPosition).getCategoryName())) {
            if (selectCategory.get(groupPosition).items.get(0)!=null&&
                    selectCategory.get(groupPosition).items.get(1)!=null) {
                selectmeal[groupPosition] = true;

            }
        } else {
            selectmeal[groupPosition] = true;
        }

        SetAdapter_list(check_day_index);
        expListView.post(new Runnable() {
            @Override
            public void run() {
                expListView.setSelectedGroup(groupPosition);
            }
        });
        //      listAdapter.notifyDataSetChanged();
/*
        selectmeal[groupPosition] = true;
        if(!categories.get(groupPosition).items.get(childPosition).isCheck()){
            categories.get(groupPosition).items.get(childPosition).setCheck(true);
            Item item = categories.get(groupPosition).items.get(childPosition);
            selectCategory.get(groupPosition).items.add(item);
        }
        else {
            categories.get(groupPosition).items.get(childPosition).setCheck(false);
            selectCategory.get(groupPosition).items.remove(childPosition);
        }
*/
        //  selectCategory.get(groupPosition).items = new ArrayList<>();
        //     listAdapter.notifyDataSetChanged();
    }

    boolean AllCheck() {
        for (int i = 0; i < selectmeal.length - 1; i++) {//because bevarge menu not availble
            if (!selectmeal[i]) {
                return false;
            }
        }
        return true;
    }

    public String getDateString(Calendar date) {
        select_Date = date;
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH) + 1;
        int day = date.get(Calendar.DAY_OF_MONTH);
        String dateStr = "" + year + "-" + month + "-" + day;


        SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
        String goal = outFormat.format(date.getTime());
        tv_dayName.setText(goal);
        check_day_index = compareToDay(start_Date, select_Date);

        // updateCheckIndex();
        return dateStr;
    }

    @Override
    public void ClickChildView(final int index) {
        for (Title title : titles) {
            if (title.isSelect()) {
                title.setSelect(false);
            }
        }
        titles.get(index).setSelect(true);
        horizontalAdapter.notifyDataSetChanged();
        expListView.post(new Runnable() {
            @Override
            public void run() {
                expListView.setSelectedGroup(index);
            }
        });
    }

    public void SetAdapter_list(final int index) {
        listAdapter = new ExpandableListAdapter(getContext(), daysCategory[index], "Meals", FullDayMealsFragment.this, isFullDayMeal);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++)
            expListView.expandGroup(i);
        expListView.post(new Runnable() {
            @Override
            public void run() {
                expListView.setSelectedGroup(index);
            }
        });
    }


    private boolean isContainData() {
        boolean flag;
        if (daysCategory[check_day_index] == null) {
            flag = false;
        } else {
            flag = true;
        }
        return flag;
    }

    public static int compareToDay(Calendar start_Date, Calendar select_Date) {
        Date date1 = start_Date.getTime();
        Date date2 = select_Date.getTime();

        if (date1 == null || date2 == null) {
            return 0;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        int index = sdf.format(date2).compareTo(sdf.format(date1));
        return index;
    }


}