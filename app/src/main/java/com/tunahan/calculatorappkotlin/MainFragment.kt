package com.tunahan.calculatorappkotlin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tunahan.calculatorappkotlin.databinding.FragmentMainBinding
import net.objecthunter.exp4j.ExpressionBuilder


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            processEditText.isFocusableInTouchMode = true
            processEditText.requestFocus()
            btn0.appendClick("0")
            btn1.appendClick("1")
            btn2.appendClick("2")
            btn3.appendClick("3")
            btn4.appendClick("4")
            btn5.appendClick("5")
            btn6.appendClick("6")
            btn7.appendClick("7")
            btn8.appendClick("8")
            btn9.appendClick("9")
            btnDecal.appendClick("-")
            btnMultiply.appendClick("*")
            btnSum.appendClick("+")
            btnDivide.appendClick("/")
            btnBrackets.appendClick("(")
            btnBrackets.setOnLongClickListener {
                binding.processEditText.append(")")
                true
            }
            btnPoint.appendClick(".")
            btnAc.setOnClickListener {
                binding.processEditText.text = null
                binding.liveResultEditText.text = ""
            }


            btnResult.setOnClickListener {
                val expression = ExpressionBuilder(binding.processEditText.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()

                if (result == longResult.toDouble()) {
                    binding.liveResultEditText.text = longResult.toString()
                } else {
                    binding.liveResultEditText.text = result.toString()
                }
            }


        }

    }

    private fun View.appendClick(string: String) {
        setOnClickListener {
            binding.processEditText.append(string)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

