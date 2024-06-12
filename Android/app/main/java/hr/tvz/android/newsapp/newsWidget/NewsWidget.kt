package hr.tvz.android.newsapp.newsWidget



import android.annotation.SuppressLint
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.widget.RemoteViews
import androidx.core.content.ContextCompat.startActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.AppWidgetTarget
import com.bumptech.glide.request.transition.Transition
import com.facebook.drawee.backends.pipeline.Fresco

import hr.tvz.android.newsapp.R


class NewsWidget : AppWidgetProvider() {
    val UPDATE_WIDGET = "AppWidgetProvider"

    var NASLOV = "Naslov"
    var DETALJI = "Detalji"
    var SLIKA = "https://ralfvanveen.com/wp-content/uploads/2021/06/Placeholder-_-Glossary.svg"

    override fun onReceive(context: Context?, intent: Intent?) {
        println(intent?.action.toString())
        if (UPDATE_WIDGET == intent!!.action) {
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val thisAppWidget = ComponentName(
                context!!.opPackageName,
                NewsWidget::class.java.name
            )

            if(intent.extras?.containsKey(NASLOV) == true) {
                NASLOV = intent.extras!!.getString(NASLOV).toString()
                DETALJI = intent.extras!!.getString(DETALJI).toString()
                SLIKA = intent.extras!!.getString(SLIKA).toString()

            }
            val appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget)
            osvjezi(context, appWidgetManager, appWidgetIds)
        }


        super.onReceive(context, intent)
    }

    @SuppressLint("RemoteViewLayout")
     fun osvjezi(
        context: Context,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?,
    ) {
        Fresco.initialize(context)
        for (appWidgetId in appWidgetIds!!) {

            val remoteViews = RemoteViews(context.packageName, R.layout.news_widget)

            remoteViews.setTextViewText(R.id.detalji, NASLOV)
            val imageUri: Uri =
                Uri.parse(SLIKA)

            val awt: AppWidgetTarget = object : AppWidgetTarget(context.applicationContext, R.id.slika, remoteViews, *appWidgetIds) {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    super.onResourceReady(resource, transition)
                }
            }

            var options = RequestOptions().
            override(300, 300).placeholder(R.drawable.news).error(R.drawable.news)

            Glide.with(context.applicationContext).asBitmap().load(SLIKA).apply(options).into(awt)


            appWidgetManager!!.updateAppWidget(appWidgetId, remoteViews)
        }
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        osvjezi(context!!, appWidgetManager, appWidgetIds)

        super.onUpdate(context, appWidgetManager, appWidgetIds)
    }

}

