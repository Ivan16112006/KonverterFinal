package com.example.myapplication;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText inputv;
    TextView result;
    Spinner valut1;
    Spinner valut2;
    double[] zna;
    String[] ValutAr;
    double n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initKurs();

// Создание ArrayAdapter, для присвоения  спиннерам значений
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.arrayValutes,
                android.R.layout.simple_spinner_item
        );
// Макет, который будет использоваться при отображении списка вариантов.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Примените адаптер к прядильщику.
        valut1.setAdapter(adapter);
        valut2.setAdapter(adapter);

        inputv.setOnKeyListener(new View.OnKeyListener() {
                                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                                        if (event.getAction() == KeyEvent.ACTION_DOWN &&
                                                (keyCode == KeyEvent.KEYCODE_ENTER)) {
                                            if (inputv.getText().length() != 0) {
                                                result.setText(otv(n));
                                            } else {
                                                result.setText(String.valueOf(0));
                                            }
                                            return true;
                                        }
                                        return false;
                                    }
                                }
        );


    }

    //Инициализация элементов активити
    public void init() {
        inputv = (EditText) findViewById(R.id.inputValut);
        result = (TextView) findViewById(R.id.otvet);
        valut1 = (Spinner) findViewById(R.id.spinnerfrom);
        valut2 = (Spinner) findViewById(R.id.spinnerto);

    }

    public void initKurs() {
        double rub = 1; //рубль
        double usd = 74.76; //доллор
        double eur = 79.61; //евро
        double jpy = 0.56; //японская иена
        double gbp = 89.79; //фунт стерлингов
        double byn = 77; //белорусский рубль9
        double cny = 10.84; //китайский юань
        zna = new double[]{rub, usd, eur, jpy, gbp, byn, cny};
        String rubs = "RUB";
        ValutAr = new String[]{"RUB", "USD", "EUR", "JPY", "GBP", "BYN", "CNY"};
    }


    public int index(String[] arr, String element) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == element) {
                return i;
            }

        }
        return -1;
    }

    String otv(double n){
        String valutaName1 = valut1.getSelectedItem().toString();
        String valutaName2 = valut2.getSelectedItem().toString();
        double znachValut1 = 0;
        double znachValut2 = 0;
        n = Double.parseDouble(String.valueOf(inputv.getText()));
        int i = 0;
        for (String a: ValutAr) {

            if (sravn(a, valutaName1)){
                znachValut1 = zna[i];

            }
            i++;
        }
        i = 0;
        for (String a: ValutAr) {
            if (sravn(a, valutaName2)){
                znachValut2 = zna[i];
            }
            i++;
        }
        double c = n*znachValut1/znachValut2;
        return String.format("%.2f", c);


    }
    boolean sravn(String a, String b){
        if (a.charAt(0) == b.charAt(0) && a.charAt(1) == b.charAt(1) && a.charAt(2) == b.charAt(2)) {
            return true;
        }
        return false;
    }
}
