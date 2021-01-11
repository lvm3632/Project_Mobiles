package mx.tec.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    TextView text, errorText;
    Button show;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView)findViewById(R.id.textView);
        errorText=(TextView)findViewById(R.id.textView2);
        show=(Button)findViewById(R.id.button);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Task().execute();
            }
        });
    }

    class Task extends AsyncTask<Void, Void, Void> {
        String records="",errors="";


        @Override
        protected Void doInBackground(Void... voids) {
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://23.145.80.54:3306/admin_movilesdb","admin_moviles","hola123");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM prueba");

                while(resultSet.next()){
                    records+=resultSet.getString(1);

                }
            }catch(Exception e){
                errors = e.toString();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
                text.setText(records);
            if(errors != "")
                errorText.setText(errors);

                super.onPostExecute(aVoid);
        }

    }
}

