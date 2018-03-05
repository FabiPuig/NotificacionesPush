package fabianpuig.example.com.notificacionespush;

import android.app.NotificationManager;
import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Fabian on 06/03/2018.
 *
 * Este servicio se encargara de las notificaciones
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        // Los mensajes mixtos pasaran por los 2 metodos

        // Mensaje con carga de notificación
        if (remoteMessage.getNotification() != null) {

            String titulo = remoteMessage.getNotification().getTitle();
            String texto = remoteMessage.getNotification().getBody();

            //Mostramos la notificación en la barra de estado
            showNotification(titulo, texto);
        }
        // Mensaje con carga de datos
        if(remoteMessage.getData() != null) {

            //log solo por testeo
            Log.i( Controller.tag, "Usuario: " + remoteMessage.getData().get( "usuario" ) );

        }
    }

    /** Método para mostrar las notificaciones con la app en primer plano
     *
     * @param title
     * @param text
     */
    private void showNotification(String title, String text) {

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.stat_sys_warning)
                        .setContentTitle(title)
                        .setContentText(text);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());

        // sonido
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
