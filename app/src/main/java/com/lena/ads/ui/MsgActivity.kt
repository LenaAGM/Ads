package com.lena.ads.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener
import com.lena.ads.R
import com.lena.ads.adapter.MsgAdapter
import com.lena.ads.databinding.ActivityMsgBinding

class MsgActivity : AppCompatActivity(), MsgAdapter.OnItemClickListener, RewardedVideoAdListener {

    private lateinit var mRewardedVideoAd: RewardedVideoAd

    lateinit var binding: ActivityMsgBinding
    lateinit var viewModel: MsgViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713")
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this)
        mRewardedVideoAd.rewardedVideoAdListener = this

        binding = DataBindingUtil.setContentView(this, R.layout.activity_msg)

        viewModel = ViewModelProviders.of(this).get(MsgViewModel::class.java)
        binding.viewModel = viewModel
        binding.executePendingBindings()

        binding.recyclerView.adapter = MsgAdapter(viewModel.messages, this)
        loadRewardedVideoAd()
    }

    override fun onPause() {
        super.onPause()
        mRewardedVideoAd.pause(this)
    }

    override fun onResume() {
        super.onResume()
        mRewardedVideoAd.resume(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mRewardedVideoAd.destroy(this)
    }

    private fun loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd(
            "ca-app-pub-3940256099942544/5224354917",
            AdRequest.Builder().build()
        )
    }

    override fun onItemClick(position: Int) {
        if (binding.viewModel!!.messages[position].author.id == 0) {
            Toast.makeText(this, binding.viewModel!!.messages[position].text, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBtnRewardClick() {
        if (mRewardedVideoAd.isLoaded) {
            mRewardedVideoAd.show()
        }
    }

    override fun onRewarded(reward: RewardItem) {
        Toast.makeText(
            this, "onRewarded! currency: ${reward.type} amount: ${reward.amount}",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onRewardedVideoAdLeftApplication() {
        Toast.makeText(this, "onRewardedVideoAdLeftApplication", Toast.LENGTH_SHORT).show()
    }

    override fun onRewardedVideoAdClosed() {
        loadRewardedVideoAd()
        Toast.makeText(this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show()
    }

    override fun onRewardedVideoAdFailedToLoad(errorCode: Int) {
        Toast.makeText(this, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show()
    }

    override fun onRewardedVideoAdLoaded() {
        Toast.makeText(this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show()
    }

    override fun onRewardedVideoAdOpened() {
        Toast.makeText(this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show()
    }

    override fun onRewardedVideoStarted() {
        Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show()
    }

    override fun onRewardedVideoCompleted() {
        Toast.makeText(this, "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show()
    }
}