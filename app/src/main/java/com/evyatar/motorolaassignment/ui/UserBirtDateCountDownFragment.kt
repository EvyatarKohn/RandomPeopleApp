package com.evyatar.motorolaassignment.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.evyatar.motorolaassignment.MainListener
import com.evyatar.motorolaassignment.R
import com.evyatar.motorolaassignment.model.usersmodel.Result
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class UserBirtDateCountDownFragment : Fragment() {
    private lateinit var mMainListener: MainListener
    private lateinit var mUserPhoto: ImageView
    private lateinit var mUserName: TextView
    private lateinit var mUserBD: TextView
    private lateinit var mUserBDCounter: TextView
    private lateinit var mResult: Result

    companion object {
        fun newInstance(result: Result, mainListener: MainListener) =
            UserBirtDateCountDownFragment().apply {
                mResult = result
                mMainListener = mainListener
            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v = inflater.inflate(R.layout.user_birtdate_countdwon_layout, container, false)

        mUserPhoto = v.findViewById(R.id.user_photo)
        UrlImageViewHelper.setUrlDrawable(mUserPhoto, mResult.picture.large)
        mUserName = v.findViewById(R.id.user_name)
        mUserName.text =
            resources.getString(R.string.name, mResult.name.first + " " + mResult.name.last)
        mUserBD = v.findViewById(R.id.user_birthdate)
        mUserBDCounter = v.findViewById(R.id.user_birthdate_counter)

        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val outputFormat = SimpleDateFormat("dd/MM/yyyy")
        val date = inputFormat.parse(mResult.dob.date)
        val formattedDate = outputFormat.format(date)

        mUserBD.text = resources.getString(R.string.bd_date, formattedDate)
        mUserBDCounter.text =
            resources.getString(R.string.days_to_next_bd, daysForBirthday(formattedDate).toString())


        return v
    }

    private fun daysForBirthday(dob: String): Long {
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        var dateOB = dob.split("/".toRegex()).toTypedArray()[0] + "/" + dob.split("/".toRegex())
            .toTypedArray()[1] + "/" + Calendar.getInstance()[Calendar.YEAR]
        if (Calendar.getInstance()[Calendar.YEAR] % 4 !== 0 && dob.split("/".toRegex())
                .toTypedArray()[0].toInt() == 29 && dob.split("/".toRegex())
                .toTypedArray()[1].toInt() == 2
        ) {
            dateOB =
                Calendar.getInstance()[Calendar.DAY_OF_MONTH].toString() +
                        Calendar.getInstance()[Calendar.MONTH].toString() +
                        Calendar.getInstance()[Calendar.YEAR].toString()
        }
        return try {
            var bday = format.parse(dateOB)
            val today = Calendar.getInstance().time
            assert(bday != null)
            if (bday!!.before(today)) {
                val c = Calendar.getInstance()
                c.time = bday
                c.add(Calendar.YEAR, 1)
                bday = Date(c.timeInMillis)
            }
            TimeUnit.DAYS.convert(bday.time - today.time, TimeUnit.MILLISECONDS)
        } catch (e: ParseException) {
            e.printStackTrace()
            -1
        }
    }
}