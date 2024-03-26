package com.example.friendlyhr.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.friendlyhr.R
import com.example.friendlyhr.databinding.FragmentSecondBinding
import com.example.friendlyhr.epoxy.model.PositionDetailEpoxyModel
import com.example.friendlyhr.data.model.Position
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment()
{

    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!

    private val args: SecondFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View?
    {

        _binding = FragmentSecondBinding.inflate(inflater , container , false)
        return binding.root

    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?)
    {
        super.onViewCreated(view , savedInstanceState)
        val position: Position = args.myData
        val detailModel = PositionDetailEpoxyModel(position)
        detailModel.bind(requireView())
        initTech(position.technologies)

        binding.fab.setOnClickListener { view ->
            // Share position when FAB is clicked
            sharePosition(position)
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun sharePosition(position: Position) {
        // Create a share intent
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out this position")
        val shareMessage = "Position: ${position.name}\nSalary Range: ${position.salaryRange}\nTechnologies: ${position.technologies.joinToString(", ")}"
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)

        // Start the activity with the share intent
        val chooser = Intent.createChooser(shareIntent , "Share Position")
        if (shareIntent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(chooser)
        } else {
            // Handle case when no activity is available to handle the intent
            Toast.makeText(requireContext() , "No app available to handle share action" , Toast.LENGTH_SHORT).show()
        }
    }

    private fun initTech(techs: List<String>) {
        val chipGroup = binding.chipGroup
        for (tech in techs) {
            val chip = layoutInflater.inflate(R.layout.skill_chip , chipGroup , false) as Chip
            chip.text = tech
            chip.isCheckable = false
            chip.textAlignment = View.TEXT_ALIGNMENT_CENTER
            chipGroup.addView(chip)
        }
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}