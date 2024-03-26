package com.example.friendlyhr.epoxy


import android.view.View
import androidx.navigation.findNavController
import com.airbnb.epoxy.TypedEpoxyController
import com.example.friendlyhr.epoxy.model.position
import com.example.friendlyhr.data.model.Position
import com.example.friendlyhr.ui.FirstFragmentDirections


class PositionController(private val listener: (Position) -> Unit) :
    TypedEpoxyController<List<Position>>()
{
    override fun buildModels(data: List<Position>)
    {
        if (data.isEmpty())
        {
            return
        }

        data.forEach { position ->
            position {
                id(position.id)
                positionName(position.name)
                salaryRange(position.salaryRange.toString())
                imageUrl(position.positionImageUrl)
                itemClickListener(View.OnClickListener {
                    val action =
                        FirstFragmentDirections.actionFirstFragmentToSecondFragment(position)
                    it.findNavController().navigate(action)
                })
            }
        }
    }
}

//class PositionDetailController : TypedEpoxyController<Position>()
//{
//    override fun buildModels(position: Position)
//    {
//        detail {
//            id(position.id)
//            positionName(position.name)
//            salaryRange(position.salaryRange)
//            imageUrl(position.positionImageUrl)
//            name(position.companyName)
//            jobDescription(position.positionDescription)
//            overview(position.companyDescription)
//        }
//    }
//}




