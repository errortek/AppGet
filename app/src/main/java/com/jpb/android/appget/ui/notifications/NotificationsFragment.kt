package com.jpb.android.appget.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.card.MaterialCardView
import com.jpb.android.appget.STActivity2
import com.jpb.android.appget.UpdateActivity
import com.jpb.android.appget.databinding.FragmentNotificationsBinding
import com.jpb.android.appget.ui.AboutActivity

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
                ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val button: MaterialCardView = binding.updatebutton
        button.setOnClickListener {
            val intent = Intent(this@NotificationsFragment.requireContext(), UpdateActivity::class.java)
            startActivity(intent)
        }
        val button2: MaterialCardView = binding.aboutbutton
        button2.setOnClickListener {
            val intent = Intent(this@NotificationsFragment.requireContext(), AboutActivity::class.java)
            startActivity(intent)
        }

        //val textView: TextView = binding.textNotifications
        //notificationsViewModel.text.observe(viewLifecycleOwner) {
        //    textView.text = it
        //}
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}