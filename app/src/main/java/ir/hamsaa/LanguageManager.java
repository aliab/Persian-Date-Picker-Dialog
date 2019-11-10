package ir.hamsaa;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;

import java.util.Locale;

public class LanguageManager {
    public static final String PERSIAN = "fa";
    public static final String ENGLISH = "en";

    public static final class ApContextWrapper extends ContextWrapper {
        public ApContextWrapper(Context base) {
            super(base);
        }

        public static ContextWrapper contextWrapper(Context context, Locale newLocale) {
            Resources res = context.getResources();
            Configuration configuration = res.getConfiguration();

            int version = Build.VERSION.SDK_INT;

            if (version >= Build.VERSION_CODES.N) {
                configuration.setLocale(newLocale);
                LocaleList localeList = new LocaleList(newLocale);
                LocaleList.setDefault(localeList);
                configuration.setLocales(localeList);
                context = context.createConfigurationContext(configuration);
            } else if (version >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                configuration.setLocale(newLocale);
                context = context.createConfigurationContext(configuration);
            } else {
                configuration.locale = newLocale;
                res.updateConfiguration(configuration, res.getDisplayMetrics());
            }

            return new ContextWrapper(context);
        }
    }
}

