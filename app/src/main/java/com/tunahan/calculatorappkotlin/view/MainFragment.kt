package com.tunahan.calculatorappkotlin.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import com.tunahan.calculatorappkotlin.R
import com.tunahan.calculatorappkotlin.databinding.FragmentMainBinding
import net.objecthunter.exp4j.ExpressionBuilder


class MainFragment : Fragment(),MenuProvider {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

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

            btnDelete.setOnClickListener {
                val expression = processEditText.text.toString()
                if (expression.isNotEmpty()) {
                    processEditText.text = expression.substring(0, expression.length - 1)
                }
            }

            btnResult.setOnClickListener {
                try {
                    val expression = ExpressionBuilder(binding.processEditText.text.toString()).build()
                    val result = expression.evaluate()
                    val longResult = result.toLong()

                    if (result == longResult.toDouble()) {
                        binding.liveResultEditText.text = longResult.toString()
                    } else {
                        binding.liveResultEditText.text = result.toString()
                    }
                }catch (e:Exception){
                    Log.d("Exception", "Message: ${e.message}")
                }
            }
        }
    }

    private fun View.appendClick(string: String) {
        setOnClickListener {
            binding.processEditText.append(string)
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
       menuInflater.inflate(R.menu.my_menu,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId){
            R.id.history_menu-> println(1)
        }
        return false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

