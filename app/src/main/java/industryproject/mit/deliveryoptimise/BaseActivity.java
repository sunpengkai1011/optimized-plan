package industryproject.mit.deliveryoptimise;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 *  BaseActivity for defining the standard of the activity.
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initListener();
    }


    /**
     * Initialise the view.
     */
    protected abstract void initView();

    /**
     * Initialise the data.
     */
    protected abstract void initData();

    /**
     * Initialise the listener.
     */
    protected abstract void initListener();
}
