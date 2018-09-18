package app.cloudcrm.tech.cloudcrm.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.airbrake.AirbrakeNotifier;

import java.util.ArrayList;
import java.util.regex.Pattern;

import app.cloudcrm.tech.cloudcrm.R;
import app.cloudcrm.tech.cloudcrm.classes.CloudCRMActivity;

public class CepActivity extends CloudCRMActivity implements View.OnClickListener {

    int []buttons = new int[]{
            R.id.b0,
            R.id.b1,
            R.id.b2,
            R.id.b3,
            R.id.b4,
            R.id.b5,
            R.id.b6,
            R.id.b7,
            R.id.b8,
            R.id.b9,
            R.id.bb,
            R.id.bok,
    };

    String value = "";

    TextView textView;


    ArrayAdapter<String> arrayAdapter;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AirbrakeNotifier.register(this, "c9c2e69d0fc6ec95ed03f201aa124902");
        setContentView(R.layout.activity_cep);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textView = (TextView) findViewById(R.id.editText);

        String tempValue = getIntent().getStringExtra("value");
        if(tempValue.length()>0){

            value = tempValue;

        }

        value = unformat(value);

        textView.setText(formatCep(value));

        setTitle(getIntent().getStringExtra("title"));

        for(int k = 0; k < buttons.length; k++){
            Button b = (Button) findViewById(buttons[k]);
            b.setOnClickListener(this);
        }



    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.bok:

                if(!isValid()){

                    new AlertDialog.Builder(this)
                            .setTitle("Cep nao valido")
                            .setMessage("O cep digitado nao e validado. Deve conter 8 digitos")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }).create().show();

                }else{

                    Intent result = new Intent();

                    result.putExtra("field", getIntent().getStringExtra("field"));

                    result.putExtra("value", value);

                    setResult(RESULT_OK, result);

                    finish();

                }

                break;
            case R.id.bb:

                try{
                    value = value.substring(0, value.length()-1);
                }catch (Exception e){
                    textView.setText("");
                }

                textView.setText(formatCep(value));

                break;
            default:

                if(value.length()<8) {

                    value = value + ((Button) view).getText().toString();

                    textView.setText(formatCep(value));

                }

                break;
        }

        if(isValid()){
            textView.setTextColor(Color.parseColor("#009909"));
        }else{
            textView.setTextColor(Color.parseColor("#990000"));
            if(value.length()==0){
                textView.setText("XXXXX-XXX");
                textView.setTextColor(Color.parseColor("#999999"));
            }
        }

    }

    boolean isValid(){
        if(value.length()==0){
            textView.setText("XXXXX-XXX");
            textView.setTextColor(Color.parseColor("#999999"));
        }
        if(value.length()==8){
            return true;
        }
        return false;
    }

    public static String formatCep(String val){

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < val.length(); i++){
            if(i == 5){
                sb.append("-");
            }

            sb.append(val.charAt(i));

        }
        return sb.toString();

    }

    String unformat(String val){
        return val.replace("-", "");
    }




}
