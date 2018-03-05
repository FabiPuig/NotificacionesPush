package fabianpuig.example.com.notificacionespush;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {

    private Button btToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btToken = findViewById( R.id.bt_token );
        btToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // obtenemos el token
                String token = FirebaseInstanceId.getInstance().getToken();

                Log.i(Controller.tag, "Token actualizado: " + token );
            }
        });
    }
}
