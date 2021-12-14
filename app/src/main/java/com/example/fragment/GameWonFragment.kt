package com.example.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fragment.databinding.FragmentGameWonBinding


class GameWonFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding:FragmentGameWonBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_game_won,container,false)
        binding.nextMatchButton.setOnClickListener { view:View ->
            view.findNavController().navigate(GameOverFragmentDirections.actionGameOverFragmentToGameFragment())
        }
       val args:GameWonFragmentArgs by navArgs()
        Toast.makeText(context,"NumCorrect: ${args.numCorrect},NumQuestions: ${args.numQuestions}",
        Toast.LENGTH_SHORT).show()

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.winner_menu,menu)
        if (null==getShareIntent().resolveActivity(activity!!.packageManager)){
            //hide menu item if doesnt resolve
            menu?.findItem(R.id.share)?.setVisible(false)

        }
    }
    private fun getShareIntent() : Intent{
        val args:GameWonFragmentArgs by navArgs()
        return ShareCompat.IntentBuilder.from(activity!!)
            .setText(getString(R.string.share_success_text,args.numCorrect,args.numQuestions))
            .setType("text/plain")
            .intent

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId){
            R.id.share ->shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun shareSuccess(){
        startActivity(getShareIntent())
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GameWonFragment().apply {

            }
    }
}