package fabianpuig.example.com.notificacionespush;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

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


        }
        // Mensaje con carga de datos
        if(remoteMessage.getData() != null) {

            //log solo por testeo
            Log.i( Controller.tag, "Usuario: " + remoteMessage.getData().get( "usuario" ) );

            //Mostramos la notificación en la barra de estado
            showNotification( remoteMessage.getData().get( "usuario" ),
                    remoteMessage.getData().get( "texto" ));
        }
    }

    /** Método para mostrar las notificaciones con la app en primer plano
     *
     * @param user
     * @param text
     */
    private void showNotification(String user, String text) {

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // custom layout
        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.custom_push);
        contentView.setImageViewResource(R.id.image, R.drawable.user_01 );
        contentView.setTextViewText(R.id.title, user + " te manda un mensaje:");
        contentView.setTextViewText(R.id.text, text);

        // icono a mostrar en la status bar y contenido (custom layout)
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.user_01)
                .setContent(contentView);

        // Notificación que mostrará
        Notification notification = mBuilder.build();
        // esconde la notificación después de seleccionarla
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        // sonido por defecto de la notificación
        notification.defaults |= Notification.DEFAULT_SOUND;
        // vibración por defecto de la notificacion
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        notificationManager.notify(1, notification);


    }
}
