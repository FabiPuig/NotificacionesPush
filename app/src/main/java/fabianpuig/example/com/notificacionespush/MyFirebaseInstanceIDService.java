package fabianpuig.example.com.notificacionespush;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Fabian on 05/03/2018.
 *
 * Este servicio es notificado sobre una posible actualizacion del token
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    /** este metodo se llamará cada vez que el token se actualice
     *
     */
    @Override
    public void onTokenRefresh() {

        String token = FirebaseInstanceId.getInstance().getToken();

        Log.i("test", "Token actualizado: " + token );
    }
}
