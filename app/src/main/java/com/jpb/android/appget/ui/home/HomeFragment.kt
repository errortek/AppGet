package com.jpb.android.appget.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.card.MaterialCardView
import com.jpb.android.appget.AppDetailsActivity
import com.jpb.android.appget.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val button3: MaterialCardView = binding.button3
        button3.setOnClickListener {
            val intent = Intent(this@HomeFragment.requireContext(), AppDetailsActivity::class.java)
            intent.putExtra("App", "AppGet")
            startActivity(intent)
        }

        val button100: MaterialCardView = binding.button100
        button100.setOnClickListener {
            val intent = Intent(this@HomeFragment.requireContext(), AppDetailsActivity::class.java)
            intent.putExtra("App", "Notes")
            startActivity(intent)
        }

        val card: MaterialCardView = binding.card
        card.setOnClickListener {
            val intent = Intent(this@HomeFragment.requireContext(), AppDetailsActivity::class.java)
            intent.putExtra("App", "ScratchTappy")
            startActivity(intent)
        }

        val card2: MaterialCardView = binding.card2
        card2.setOnClickListener {
            val intent = Intent(this@HomeFragment.requireContext(), AppDetailsActivity::class.java)
            intent.putExtra("App", "LibChecker")
            startActivity(intent)
        }

        //val textView: TextView = binding.textHome
        //homeViewModel.text.observe(viewLifecycleOwner) {
        //    textView.text = it
        //}
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}