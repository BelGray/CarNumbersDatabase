package com.example.carnumbersdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivityBad extends AppCompatActivity implements View.OnClickListener{

    private Button searchButton;
    private Button addNumbersButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchButton = (Button) findViewById(R.id.searchButton); // Убираем каст (Button) - это лютый легаси.
        searchButton.setOnClickListener(this); // ты имплеменитировал View.OnClickListener

    }

    // и реализация метода нажатия вот тут.
    public void onClick(View v){

        // внутри данного метода будет проиходить обработка нажатия на кнопку, тоесть как только
        // пользователь нажмет на searchButton код попадет в метод onClick()



        // Здесь получается следующее - как только пользователь нажмет на кнопку, будет еще одна
        // обработка нажатия на кнопку. Это будет звучать так - принажатии на кнопку будет
        // происходить объяснении кнопки на на нее нажимать.
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Как я понимаю тут проиходит переход на новую активити. Но, не надо так.
                setContentView(R.layout.activity_show_numbers_info);
            }
        });

        // в MainActivity точно накой же код, но правиьно написанный.
    }

}