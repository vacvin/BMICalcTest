package vacvin.myapplication1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//import java.text.DecimalFormat;


public class MainActivity2 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        initViews();
        showResults();
        setListeners();
    }

    private Button btn_back;
    private TextView show_result;

    private void initViews(){
        btn_back = (Button)findViewById(R.id.back);
        show_result =(TextView)findViewById(R.id.result);
    }

    private void showResults(){
        Bundle bundle = this.getIntent().getExtras();
        String result = bundle.getString("result");
        show_result.setText(result);
    }

    private  void setListeners(){
        btn_back.setOnClickListener(goback);
    }

    private View.OnClickListener goback = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //back
            Bundle bundle = new Bundle();
            Intent intent = new Intent();

            bundle.putString("BMI", show_result.getText().toString());
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            MainActivity2.this.finish();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
