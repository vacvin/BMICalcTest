package vacvin.myapplication1;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
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
//import android.view.MenuInflater;
import android.content.DialogInterface;
import android.widget.Toast;

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

    //protected static final int MENU_SETTINGS = Menu.FIRST;

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //menu.add(0, MENU_SETTINGS, 100, R.string.action_settings);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        switch(id) {
            case R.id.action_about:
                openOptionsDialog();
                break;
            case R.id.action_close:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openOptionsDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle(R.string.action_title);
        dialog.setMessage(R.string.action_msg);

        //新增ok按鈕
        dialog.setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener(){
                    public void onClick(
                        DialogInterface dialoginterface, int i){}
                });

        //新增google首頁按鈕 點擊後開啟瀏覽器
        dialog.setNegativeButton(R.string.label_homepage,
                new DialogInterface.OnClickListener(){
                    public  void onClick(
                        DialogInterface dialoginterface, int i){
                            // 開啟瀏覽器
                            Uri uri = Uri.parse("http://www.google.com");
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                        }
                });

        //新增google map按鈕 點擊後開啟google map
        dialog.setNegativeButton(R.string.label_googleMap,
                new DialogInterface.OnClickListener(){
                    public  void onClick(
                            DialogInterface dialoginterface, int i){
                        // 開啟瀏覽器
                        Uri uri = Uri.parse("geo:25.047192, 121.516981?z=17");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });

        dialog.show();
    }

    private void showToastDialog(String msg) {
        Toast popup = Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT);
        popup.show();
    }

    private OnClickListener calcBMI = new OnClickListener() {
        @Override
        public void onClick(View v) {
            DecimalFormat nf = new DecimalFormat("0.00");

            try {
                double height =
                        Double.parseDouble(num_height.getText().toString())/100;
                double weight =
                        Double.parseDouble(num_weight.getText().toString());
                double BMI = weight / ( height * height);

                show_result.setText(getText(R.string.bmi_result).toString() +
                        getText(R.string.bmi_str1).toString() +
                        nf.format(BMI));
            }catch (Exception e) {
                showToastDialog(getText(R.string.calc_err01).toString());
            }

            //open activity2
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        }
    };
}
