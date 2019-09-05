package com.example.atry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;


public class MainActivity extends AppCompatActivity {
    EditText female, male;
    Button calcualte;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        female = (EditText) findViewById(R.id.female);
        male = (EditText) findViewById(R.id.male);
        calcualte = (Button) findViewById(R.id.calculate);
        result = (TextView) findViewById(R.id.result);
        calcualte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String str1 = male.getText().toString().toLowerCase();
                String str2 = female.getText().toString().toLowerCase();
                String calculation = calculate(str1, str2);
                result.setText((calculation));
            }
        });
    }

    String countChar(String str1, String str2)
    {
        String combinedString = str1 + "loves" + str2;
        String strAllChars = "";
        String strCount = "";
        for (char c1 : combinedString.toCharArray())
        {
            if (strAllChars.indexOf(c1) < 0)
            {
                int count = 0;
                for (char c2 : combinedString.toCharArray())
                {
                    if (c1 == c2)
                        count = count + 1;
                }

                strAllChars = strAllChars + c1;
                strCount = strCount + String.valueOf(count);
            }
        }
        return strCount;
    }

    String shortenNumber(String str) {
        String shortenString = "";
        if (str.length() >= 2) {
            int int1 = Integer.parseInt(String.valueOf(str.toCharArray()[0]));
            int int2 = Integer.parseInt(String.valueOf(str.toCharArray()[str.length() - 1]));
            shortenString = String.valueOf(int1 + int2) + shortenNumber(str.substring(1, str.length() - 1));
        } else
            {
            return str;
            }
        return shortenString;
    }

    static String calculate(String male, String female)
    {
        String data = female + " Loves " + male;
        //
        Map<Integer, Long> collect = data.chars().boxed().collect(groupingBy(Function.identity(), counting()));
        String percentage = collect.values().stream().map(it -> "" + it).collect(Collectors.joining());
        do {

            String newPer = "";
            for (int i = 0; i < percentage.length() / 2; i++) {
                int a = (percentage.charAt(i) - '0');
                a += percentage.charAt(percentage.length() - i - 1) - '0';
                newPer += a;
            }
            if(percentage.length()%2!=0)
                newPer+=percentage.charAt(percentage.length()/2);
            // System.out.println(newPer);
            percentage = newPer;
        } while (percentage.length() > 2);

        return percentage+"%";

    }
}