package com.capstone.hewankita.ui.care

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.capstone.hewankita.R
import com.capstone.hewankita.databinding.FragmentCareBinding
import com.capstone.hewankita.ui.bottom.BottomActivity
import com.capstone.hewankita.utils.OptionDialogFragment

class CareFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentCareBinding? = null
    private val binding get() = _binding!!

    private var mYear = 0
    private var mMonth = 0
    private var mDay = 0
    private var mHour = 0
    private var mMinute = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCareBinding.inflate(inflater, container, false)
        val root: View = binding.root

        activity?.title = getString(R.string.care)

        binding.tvOutlet.setOnClickListener(this)
        binding.tvCheckIn.setOnClickListener(this)
        binding.tvCheckOut.setOnClickListener(this)
        binding.tvTimeOfArrival.setOnClickListener(this)
        binding.btnNext.setOnClickListener(this)

        return root
    }

    override fun onClick(v: View?) {
        if(v == binding.tvOutlet) {
            val mOptionDialogFragment = OptionDialogFragment()
            val mFragmentManager = childFragmentManager
            mOptionDialogFragment.show(mFragmentManager, OptionDialogFragment::class.java.simpleName)
        }
        if (v === binding.tvCheckIn) {
            val c = Calendar.getInstance()
            mYear = c[Calendar.YEAR]
            mMonth = c[Calendar.MONTH]
            mDay = c[Calendar.DAY_OF_MONTH]

            val datePickerDialog = DatePickerDialog(requireContext(), { _, year, monthOfYear, dayOfMonth ->
                binding.tvCheckIn.setText(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
            }, mYear, mMonth, mDay)

            datePickerDialog.show()
        }
        if (v === binding.tvCheckOut) {
            val c = Calendar.getInstance()
            mYear = c[Calendar.YEAR]
            mMonth = c[Calendar.MONTH]
            mDay = c[Calendar.DAY_OF_MONTH]

            val datePickerDialog = DatePickerDialog(requireContext(), { _, year, monthOfYear, dayOfMonth ->
                binding.tvCheckOut.setText(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
            }, mYear, mMonth, mDay)

            datePickerDialog.show()
        }
        if (v === binding.tvTimeOfArrival) {
            val c = Calendar.getInstance()
            mHour = c[Calendar.HOUR_OF_DAY]
            mMinute = c[Calendar.MINUTE]

            val timePickerDialog = TimePickerDialog(requireContext(), { _, hourOfDay, minute ->
                binding.tvTimeOfArrival.setText("$hourOfDay:$minute")
            }, mHour, mMinute, true)

            timePickerDialog.show()
        }
        if (v == binding.btnNext) {
            var isEmptyFields = false

            if(binding.tvOutlet.text.isEmpty()) {
                isEmptyFields = true
                binding.tvOutlet.error = "Nilai tidak boleh kosong"
            }
            if(binding.tvCheckIn.text.isEmpty()) {
                isEmptyFields = true
                binding.tvCheckIn.error = "Nilai tidak boleh kosong"
            }
            if(binding.tvCheckOut.text.isEmpty()) {
                isEmptyFields = true
                binding.tvCheckOut.error = "Nilai tidak boleh kosong"
            }
            if(binding.tvTimeOfArrival.text.isEmpty()) {
                isEmptyFields = true
                binding.tvTimeOfArrival.error = "Nilai tidak boleh kosong"
            }
            if(!isEmptyFields) {
                val intent = Intent(requireActivity(), BottomActivity::class.java)
                startActivity(intent)
                Toast.makeText(requireActivity(), resources.getString(R.string.booking_success), Toast.LENGTH_SHORT).show()
                activity?.finish()
            }
        }
    }

    internal var optionDialogListener: OptionDialogFragment.OnOptionDialogListener = object : OptionDialogFragment.OnOptionDialogListener {
        override fun onOptionChosen(text: String?) {
            binding.tvOutlet.setText(text)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}