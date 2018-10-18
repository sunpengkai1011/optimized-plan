package industryproject.mit.deliveryoptimise.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import industryproject.mit.deliveryoptimise.R;
import industryproject.mit.deliveryoptimise.adapter.AddressesAdapter;

public class HomeActivity extends BaseActivity {
    private TextView tv_title;
    private ListView lv_item;

    @Override
    void initView() {
        setContentView(R.layout.activity_home);
        tv_title = findViewById(R.id.tv_title);
        lv_item = findViewById(R.id.lv_item);
    }

    @Override
    void initData() {
        tv_title.setText("Home");

        List<String> items = new ArrayList<>();
        items.add("The first addresses");
        items.add("The second addresses");
        items.add("The third addresses");
        items.add("The fourth addresses");
        AddressesAdapter adapter = new AddressesAdapter(this, items, 0);
        lv_item.setAdapter(adapter);
    }

    @Override
    void initListener() {
        lv_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(HomeActivity.this, AddressListActivity.class);
                intent.putExtra("addresses", i);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}
