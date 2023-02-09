package com.tunahan.calculatorappkotlin.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.tunahan.calculatorappkotlin.R
import com.tunahan.calculatorappkotlin.databinding.FragmentMainBinding
import com.tunahan.calculatorappkotlin.model.History
import com.tunahan.calculatorappkotlin.viewmodel.CalculatorViewModel
import net.objecthunter.exp4j.ExpressionBuilder


class MainFragment : Fragment()//,MenuProvider
 {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var mCalculatorViewModel: CalculatorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        mCalculatorViewModel = ViewModelProvider(this).get(CalculatorViewModel::class.java)

        //val menuHost: MenuHost = requireActivity()
       // menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val appName = resources.getString(R.string.app_name)
        //menu
        binding.toolbar.inflateMenu(R.menu.my_menu)
        binding.toolbar.title=appName

        binding.toolbar.setOnMenuItemClickListener{
            when(it.itemId){
                R.id.history_menu-> {
                    val action =MainFragmentDirections.actionMainFragmentToHistoryFragment()
                    view.let { Navigation.findNavController(it).navigate(action) }
                    true
                }
                else->false
            }

        }

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

                insertDataToDatabase()
            }
        }
    }

    private fun View.appendClick(string: String) {
        setOnClickListener {
            binding.processEditText.append(string)
        }
    }



    private fun insertDataToDatabase(){

        val process = binding.processEditText.text.toString()
        var result = binding.liveResultEditText.text.toString()
        result = "=$result"

        val historys = History(0,process,result)

        mCalculatorViewModel.addHistory(historys)

    }

     /*
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
       menuInflater.inflate(R.menu.my_menu,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId){
            R.id.history_menu-> {
                val action =MainFragmentDirections.actionMainFragmentToHistoryFragment()
                view?.let { Navigation.findNavController(it).navigate(action) }
            }
        }
        return false
    }
*/
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

