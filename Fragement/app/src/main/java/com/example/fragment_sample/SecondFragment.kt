package com.example.fragment_sample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.fragment_sample.databinding.FragmentSecondBinding
import com.example.fragment_sample.model.Profile


class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    // ビューが作成された直後に呼ばれるメソッド。
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // argumentsでFragmentに渡されたメニューデータを取得し、それを利用してUIを更新する。
        parentFragmentManager.setFragmentResultListener(FirstFragment.REQUEST_PROFILE_DETAIL, this) { _, bundle ->
            val selectedProfile: Profile = bundle.getParcelable("SELECTED_PROFILE")!!
            binding.tvName.text = selectedProfile.name
            binding.tvCategory.text = selectedProfile.category.toString()
            // 演習：詳細内容を追記してください。
            binding.tvDetail.text = selectedProfile.detail



            Glide.with(binding.root.context)
                .load(selectedProfile.imgResID)
                .into(binding.ivProfile1)

            Glide.with(binding.root.context)
                .load(selectedProfile.imgResID2)
                .into(binding.ivProfile2) // 두 번째 ImageView
        }
    }
}