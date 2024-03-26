package com.example.friendlyhr.epoxy.model

import android.view.View
import android.view.ViewParent
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.example.friendlyhr.R
import com.example.friendlyhr.databinding.PositionItemCardBinding

@EpoxyModelClass()
abstract class PositionModel : EpoxyModelWithHolder<PositionModel.CardHolder>()
{

    @field:EpoxyAttribute
    open var positionName: String? = null

    @field:EpoxyAttribute
    open var salaryRange: String? = null

    @field:EpoxyAttribute
    open var imageUrl: String? = null

    @field:EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    open var itemClickListener: View.OnClickListener? = null


    // Bind our data to view
    override fun bind(holder: CardHolder)
    {
        holder.binding.apply {
            positionTitle.text = positionName
            positionSalaryRange.text = salaryRange
            Glide.with(positionImage)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .fitCenter().centerCrop()
                .into(positionImage)
            root.setOnClickListener(itemClickListener)
        }
    }

    // Unbind listeners or cancel requests etc.
    override fun unbind(holder: CardHolder)
    {
        holder.binding.root.setOnClickListener(null)
    }

    class CardHolder : EpoxyHolder()
    {
        lateinit var binding: PositionItemCardBinding
            private set

        override fun bindView(itemView: View)
        {
            binding = PositionItemCardBinding.bind(itemView)
        }
    }

    override fun getDefaultLayout(): Int
    {
        return R.layout.position_item_card
    }

    override fun createNewHolder(parent: ViewParent): CardHolder
    {
        return CardHolder()
    }
}