package com.example.myapplication.affirmations.data

import android.adservices.topics.Topic
import com.example.myapplication.R
import com.example.myapplication.affirmations.model.Affirmation
import com.example.myapplication.affirmations.model.JobTopic

class Datasource() {
    fun loadAffirmations(): List<Affirmation> {
        return listOf<Affirmation>(
            Affirmation(R.string.affirmation1, R.drawable.image1),
            Affirmation(R.string.affirmation2, R.drawable.image2),
            Affirmation(R.string.affirmation3, R.drawable.image3),
            Affirmation(R.string.affirmation4, R.drawable.image4),
            Affirmation(R.string.affirmation5, R.drawable.image5),
            Affirmation(R.string.affirmation6, R.drawable.image6),
            Affirmation(R.string.affirmation7, R.drawable.image7),
            Affirmation(R.string.affirmation8, R.drawable.image8),
            Affirmation(R.string.affirmation9, R.drawable.image9),
            Affirmation(R.string.affirmation10, R.drawable.image10))
    }
}

class Jobs(){
    fun loadTopics() : List<JobTopic>{
        return listOf<JobTopic>(
            JobTopic(R.string.architecture, 58, R.drawable.architecture),
            JobTopic(R.string.crafts, 121, R.drawable.crafts),
            JobTopic(R.string.business, 78, R.drawable.business),
            JobTopic(R.string.culinary, 118, R.drawable.culinary),
            JobTopic(R.string.design, 423, R.drawable.design),
            JobTopic(R.string.fashion, 92, R.drawable.fashion),
            JobTopic(R.string.film, 165, R.drawable.film),
            JobTopic(R.string.gaming, 164, R.drawable.gaming),
            JobTopic(R.string.drawing, 326, R.drawable.drawing),
            JobTopic(R.string.lifestyle, 305, R.drawable.lifestyle),
            JobTopic(R.string.music, 212, R.drawable.music),
            JobTopic(R.string.painting, 172, R.drawable.painting),
            JobTopic(R.string.photography, 321, R.drawable.photography),
            JobTopic(R.string.tech, 118, R.drawable.tech),
            JobTopic(R.string.architecture, 58, R.drawable.architecture),
            JobTopic(R.string.crafts, 121, R.drawable.crafts),
            JobTopic(R.string.business, 78, R.drawable.business),
            JobTopic(R.string.culinary, 118, R.drawable.culinary),
            JobTopic(R.string.design, 423, R.drawable.design),
            JobTopic(R.string.fashion, 92, R.drawable.fashion),
            JobTopic(R.string.film, 165, R.drawable.film),
            JobTopic(R.string.gaming, 164, R.drawable.gaming),
            JobTopic(R.string.drawing, 326, R.drawable.drawing),
            JobTopic(R.string.lifestyle, 305, R.drawable.lifestyle),
            JobTopic(R.string.music, 212, R.drawable.music),
            JobTopic(R.string.painting, 172, R.drawable.painting),
            JobTopic(R.string.photography, 321, R.drawable.photography),
            JobTopic(R.string.tech, 118, R.drawable.tech)
        )
    }
}