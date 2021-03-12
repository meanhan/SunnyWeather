package com.hanxu.sunnyweather.ui.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.hanxu.sunnyweather.R
import com.hanxu.sunnyweather.logic.model.DailyResponse
import com.hanxu.sunnyweather.logic.model.Place
import com.hanxu.sunnyweather.logic.model.getSky
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class ForecastAdapter(
    private val skyList: List<DailyResponse.Skycon>,
    private val temperatureList: List<DailyResponse.Temperature>
) : RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dateInfo: TextView = view.findViewById(R.id.dateInfo)
        val skyIcon: ImageView = view.findViewById(R.id.skyIcon)
        val skyInfo: TextView = view.findViewById(R.id.skyInfo)
        val temperatureInfo: TextView = view.findViewById(R.id.temperatureInfo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.forecast_item, parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val skycon = skyList[position]
        val temperature = temperatureList[position]
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = simpleDateFormat.parse(skycon.date, ParsePosition(0)).time
        holder.dateInfo.text = simpleDateFormat.format(date)

        val sky = getSky(skycon.value)
        holder.skyIcon.setImageResource(sky.icon)
        holder.skyInfo.text = sky.info
        val tempText = "${temperature.min.toInt()} ~ ${temperature.max.toInt()} ℃"
        holder.temperatureInfo.text = tempText
    }

    override fun getItemCount(): Int {
        return skyList.size
    }
}