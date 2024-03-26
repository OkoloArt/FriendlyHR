package com.example.friendlyhr.epoxy.model

import com.bumptech.glide.Glide
import com.example.friendlyhr.R
import com.example.friendlyhr.databinding.FragmentSecondBinding
import com.example.friendlyhr.data.model.Position

data class PositionDetailEpoxyModel(
        val position: Position
) : ViewBindingKotlinModel<FragmentSecondBinding>(R.layout.fragment_first){
    override fun FragmentSecondBinding.bind()
    {
        jobTitle.text = position.name
        companyName.text = position.companyName
        positionSalaryRange.text = position.salaryRange.toString()
        positionDescription.text = position.positionDescription
        companyOverview.text = position.companyDescription
        Glide.with(positionImageUrl)
            .load(position.positionImageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .fitCenter().centerCrop()
            .into(positionImageUrl)
    }

}
