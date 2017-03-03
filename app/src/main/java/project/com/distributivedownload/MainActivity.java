package project.com.distributivedownload;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText url,key;
    private Button download;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        url = (EditText) findViewById(R.id.link);
        key = (EditText) findViewById(R.id.key);
        download = (Button) findViewById(R.id.download);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!url.getText().toString().contains(" ")&&!key.getText().toString().contains(" ")&&!url.getText().toString().equals("")&&!key.getText().toString().equals(""))
                    Toast.makeText(context,"Download has started",Toast.LENGTH_SHORT).show();
                else
                {
                    if(url.getText().toString().contains(" ")||url.getText().toString().equals(""))
                        url.setError("url cannot contain blank spaces");
                    if(key.getText().toString().contains(" ")||key.getText().toString().equals(""))
                        key.setError("key cannot contain blank spaces");
                }
            }
        });
    }
}
