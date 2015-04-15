package vacvin.myapplication1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import java.text.DecimalFormat;
//import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setListeners();
    }

    private Button button_calc;
    private EditText num_height;
    private EditText num_weight;
    private TextView show_result;

    private void initViews() {
        button_calc = (Button)findViewById(R.id.button);
        num_height = (EditText)findViewById(R.id.editText);
        num_weight = (EditText)findViewById(R.id.editText2);
        show_result = (TextView)findViewById(R.id.textView4);
    }

    private  void setListeners(){
        button_calc.setOnClickListener(calcBMI);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private OnClickListener calcBMI = new OnClickListener() {
        @Override
        public void onClick(View v) {
            DecimalFormat nf = new DecimalFormat("0.00");
            double height =
                    Double.parseDouble(num_height.getText().toString())/100;
            double weight =
                    Double.parseDouble(num_weight.getText().toString());
            double BMI = weight / ( height * height);

            show_result.setText(getText(R.string.bmi_result).toString() +
                                getText(R.string.bmi_str1).toString() +
                                nf.format(BMI));
        }
    };
}
