package fabianpuig.example.com.notificacionespush;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {

    private TextView tvUser;
    private Button btToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvUser = findViewById( R.id.tv_usuario );
        btToken = findViewById( R.id.bt_token );

        Bundle bundle = getIntent().getExtras();
        if( bundle != null ){
            tvUser.setText( bundle.getString( "usuario" ) );
        }

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
