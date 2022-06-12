package com.example.inclusive.view.fragments


import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.compose.ui.text.toUpperCase
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inclusive.R
import com.example.inclusive.model.adapter.braille.BrailleAdapter
import com.example.inclusive.model.adapter.dagtololo.DagtiloloAdapter
import com.example.inclusive.model.adapter.translate.EspaolAdapter
import com.example.inclusive.model.adapter.translate.EspaolViewHolder
import com.example.inclusive.model.provider.braille.Braille
import com.example.inclusive.model.provider.braille.BrailleProvider
import com.example.inclusive.model.provider.dagtilolo.DagtiloloProvider
import com.example.inclusive.model.provider.espaol.EspaolProvider
import com.example.inclusive.viewmodel.FirebaseViewModel
import com.example.inclusive.viewmodel.HomeViewModel
import com.example.inclusive.viewmodel.TranslatespaintoViewModel
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private val HomeviewModel: HomeViewModel by activityViewModels()
    private val translatespaintoViewModel:TranslatespaintoViewModel by activityViewModels()
    private val firebaseViewModel:FirebaseViewModel by activityViewModels()
    private lateinit var editText:EditText
    private lateinit var spLista: Spinner
    private lateinit var spLista2: Spinner
    private lateinit var buttonflot:View
    var layoutManager:RecyclerView.LayoutManager?=null
    var adapter:RecyclerView.Adapter<EspaolViewHolder>?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        spLista = view.findViewById(R.id.spinner_1)
        spLista2 = view.findViewById(R.id.spinner_2)
        editText= view.findViewById(R.id.getText_home)
        buttonflot=view.findViewById(R.id.buttonfab)
        //Botones del teclado Braille

        Spinner1()
        Spinner2()

        HomeviewModel.selectedItem1.observe(viewLifecycleOwner) { it ->

            HomeviewModel.selectedItem2.observe(viewLifecycleOwner) { at ->

                translateOptions(it,at)
            }

        }

    }

    fun translateOptions(item1: Int?, item2: Int?){

        if(item1 !=null && item2!=null){
            var option=item1.toString()+item2.toString()
            Log.e("itemssum",option)

            when(option){
                "00"->{
                    BrailleProvider.brailleList.clear()
                    translateEspanoltoBraille()
                }
                "01"->{
                    translateEspaoltoDatololo()
                  }
            }
        }
    }

    private fun translateEspaoltoEspaol(){
        buttonflot.setOnClickListener {
            EspaolProvider.espaolList.clear()
            var text:String=editText.text.toString().trim()
            translatespaintoViewModel.setListEspaol(text)
            translatespaintoViewModel.obtenerMutable.observe(viewLifecycleOwner){
                if(it !=null){
                    initRecyclerViewEspaol()
                    editText.text.clear()
                }
            }

        }
    }

    private fun translateEspanoltoBraille(){

        buttonflot.setOnClickListener {
            BrailleProvider.brailleList.clear()
            DagtiloloProvider.datiloloList.clear()
            var text:String=editText.text.toString().trim().toUpperCase()
            translatespaintoViewModel.setListBraille(text.toUpperCase())
            translatespaintoViewModel.obtenerMutable.observe(viewLifecycleOwner){
                if(it !=null){initRecyclerViewbraille()
                    editText.text.clear()}
            }
        }
    }


    private fun translateEspaoltoDatololo(){
        buttonflot.setOnClickListener {
            BrailleProvider.brailleList.clear()
            DagtiloloProvider.datiloloList.clear()
            var text:String=editText.text.toString().trim()
            firebaseViewModel.downloadimage(text,requireContext())
            firebaseViewModel.downloadMutable.observe(
                viewLifecycleOwner){
                if(it!=null){
                    Log.e("imagen",it)
                    initRecyclerViewDactilolo()
                    editText.text.clear()
                }
            }
        }

    }

    private fun initRecyclerViewEspaol(){

        recyclerViewshow.apply {
            layoutManager=GridLayoutManager(activity,4)
            adapter=EspaolAdapter(EspaolProvider.espaolList)
        }
    }

  private fun initRecyclerViewbraille(){

        recyclerViewshow.apply {
            layoutManager=GridLayoutManager(activity,4)
           adapter=BrailleAdapter(BrailleProvider.brailleList)
        }
    }
    private fun initRecyclerViewDactilolo(){

        recyclerViewshow.apply {
            layoutManager=GridLayoutManager(activity,3)
           adapter=DagtiloloAdapter(DagtiloloProvider.datiloloList)
        }
    }
    private fun Spinner1(){
        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.Lista1,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spLista.adapter = adapter
        }

        spLista.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                HomeviewModel.setitem1(position)

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
               //HomeviewModel.setitem1(p0)
            }
        }
    }
    private fun Spinner2(){
        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.Lista2,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spLista2.adapter = adapter
        }

        spLista2.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                HomeviewModel.setItem2(position)

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


    }
        companion object {
            fun newInstance() = HomeFragment()
                            }

}