package com.example.jteam.friender;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;


public class MainActivity extends Activity {
    CityAdapter Adapter;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Complete
        ArrayList<String> main_city_list = new ArrayList<String>();
        //사진 리스트를 담기위한 어래이리스트
        ArrayList<Integer> plist = new ArrayList<Integer>();
        CityList CList= new CityList();


        // get city names (city list)
        main_city_list=CList.getCity_list();
        //get city pictures
        plist = CList.getCity_plist();

        // preparation adapter
        //ArrayAdapter<String> Adapter;
        Adapter = new CityAdapter(main_city_list, plist);//시티어탭터로 리스트뷰에 연결

        //Adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, main_city_list);

        // connection adapter
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(Adapter);


        // listview options
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        list.setDivider(new ColorDrawable(Color.WHITE));
        list.setDividerHeight(2);

        //리스트뷰 클릭 리스너
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Selected : " + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),BoardActivity.class);

                //인텐트에 position정보를 담아 전달
                intent.setFlags(position);
                startActivity(intent);
            }

        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.jteam.friender/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.jteam.friender/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    // 리스트뷰에 데이터를 연결하기위한 어댑터
    class CityAdapter extends BaseAdapter {
        ArrayList<String> cities;
        ArrayList<Integer> pcities;

        //시티리스트(이름, 사진)를 받아와 초기화
        public CityAdapter(ArrayList<String> list, ArrayList plist)
        {
            cities = list;
            pcities = plist;
        }

        @Override
        public int getCount() {
            return cities.size();
        }

        @Override
        public Object getItem(int position) {
            return cities.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        //각 리스트에 보여질 뷰 세팅
        public View getView(int position, View convertView, ViewGroup parent) {

            CityItemView view = new CityItemView(getApplicationContext());

            view.setImage(pcities.get(position));
            view.setName(cities.get(position));

            return view;
        }
    }

}



    // ActionBar Option // 액션연습한건데 잘안됨
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){
//        getMenuInflater().inflate(R.menu.actionbar_menu,menu);
//        return true;
//    }
//
//    @Overrided
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if ( id == R.id.newID ) {
//            Toast.makeText(MainActivity.this, "새 글 등록 버튼 클릭", Toast.LENGTH_SHORT).show();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//}
