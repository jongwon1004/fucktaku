package com.example.fragment_sample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fragment_sample.adapter.ProfileAdapter
import com.example.fragment_sample.databinding.FragmentFirstBinding
import com.example.fragment_sample.model.Profile

class FirstFragment : Fragment() {

    private lateinit var binding:FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    // ビューが作成された直後に呼ばれるメソッド
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 表示するプロフィールリスト
        val profileList = listOf<Profile>(
            Profile(getString(R.string.carbonara), getString(R.string.carbonara_category), getString(R.string.carbonara_des), R.drawable.carbonara, R.drawable.hedder),
            Profile(getString(R.string.cornsoup), getString(R.string.cornsoup_category), getString(R.string.cornsoup_des), R.drawable.cornsoup, R.drawable.hedder),
            Profile(getString(R.string.doria), getString(R.string.doria_category), getString(R.string.doria_des), R.drawable.doria, R.drawable.hedder),
            Profile(getString(R.string.focaccia), getString(R.string.focaccia_category), getString(R.string.focaccia_des), R.drawable.focaccia, R.drawable.hedder),
            Profile(getString(R.string.guratan), getString(R.string.guratan_category), getString(R.string.guratan_des), R.drawable.guratan, R.drawable.hedder),
            Profile(getString(R.string.hamburg), getString(R.string.hamburg_category), getString(R.string.hamburg_des), R.drawable.hamburg, R.drawable.hedder)
        )

        // ハンズオン演習：クリック時処理とfragmentの画面遷移処理を実装
        val adapter = ProfileAdapter(profileList) {
            // onProfileClicked(Profile)の実際に行う処理を記述
            selectProfile ->
            // データの格納
            parentFragmentManager.setFragmentResult(
                REQUEST_PROFILE_DETAIL,
                bundleOf("SELECTED_PROFILE" to selectProfile)
            )
            // 次のFragmentへ遷移
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, SecondFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.rvProfilelist.adapter = adapter
        binding.rvProfilelist.layoutManager = GridLayoutManager(context,2)

    }
    companion object{
        val REQUEST_PROFILE_DETAIL = "REQUEST_PROFILE_DETAIL"
    }
}