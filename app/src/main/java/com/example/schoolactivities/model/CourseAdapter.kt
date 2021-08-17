package com.example.schoolactivities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.schoolactivities.model.Courses

class CourseAdapter(var courseList: List<Courses>): RecyclerView.Adapter<CoursesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        var itemView=LayoutInflater.from(parent.context)
            .inflate(R.layout.list_of_courses,parent,false)
        return CoursesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {
        var currentCourses=courseList.get(position)
        holder.tvCourseName.text=currentCourses.courseName
        holder.tvCourseCode.text=currentCourses.courseCode
        holder.tvCourseDescription.text=currentCourses.description
        holder.tvCourseTrainer.text=currentCourses.trainer
    }

    override fun getItemCount(): Int {
        return courseList.size
    }
}

class CoursesViewHolder (itemView:View):RecyclerView.ViewHolder(itemView){
    var tvCourseName=itemView.findViewById<TextView>(R.id.tvCourseName)
    var tvCourseCode=itemView.findViewById<TextView>(R.id.tvCourseCode)
    var tvCourseDescription=itemView.findViewById<TextView>(R.id.tvCourseDescription)
    var tvCourseTrainer=itemView.findViewById<TextView>(R.id.tvCOurseTrainer)

}


