package com.g7.gatgo.adapter

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ImageSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView

import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequest
import com.g7.gatgo.R
import com.g7.gatgo.activity.Test
import com.g7.gatgo.pojo.FeedPojo

import java.util.ArrayList

import com.g7.gatgo.utils.CommonActions

/**
 * Created by newagesmb on 8/3/18.
 */

class FeedAdapter// Provide a suitable constructor (depends on the kind of dataset)
(myDataset: ArrayList<FeedPojo>, internal var context: Context, internal var isFav: Boolean, private val m_user_id: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    //    private val extractorsFactory: DefaultExtractorsFactory
//    private val trackSelector: DefaultTrackSelector
//    private val mediaDataSourceFactory: DefaultDataSourceFactory
//    private val bandwidthMeter: DefaultBandwidthMeter
    internal var feedList = ArrayList<FeedPojo>()
    //    private var player: SimpleExoPlayer? = null
    internal var commonActions: CommonActions
    //    private var mExoPlayerFullscreen = false
    internal var bq_id: Int = 0
    internal var q_id: Int = 0

    //    internal var selectedVideoUrl: String? = null//feedList.get(getAdapterPosition()).getVideoUrl()
    internal var position = 0

//    private val playerLis = object : ExoPlayer.EventListener() {
//        fun onTimelineChanged(timeline: Timeline, manifest: Any, reason: Int) {
//
//        }
//
//        fun onTracksChanged(trackGroups: TrackGroupArray, trackSelections: TrackSelectionArray) {
//
//        }
//
//        fun onLoadingChanged(isLoading: Boolean) {
//
//        }
//
//        fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
//            var stateString = ""
//            when (playbackState) {
//                ExoPlayer.STATE_IDLE -> stateString = "ExoPlayer.STATE_IDLE      -"
//                ExoPlayer.STATE_BUFFERING -> {
//                    //                    if (feedList.get(position).isSkippable()) feedList.get(position).setSkippable(false);
//                    feedList[position].setShowProgress(true)
//                    notifyDataSetChanged()
//                    stateString = "ExoPlayer.STATE_BUFFERING -"
//                }
//                ExoPlayer.STATE_READY -> {
//                    feedList[position].setShowProgress(false)
//                    if (feedList[position].isSkippable())
//                        feedList[position].setSkippable(true)
//                    stateString = "ExoPlayer.STATE_READY     -"
//                    notifyDataSetChanged()
//                }
//                ExoPlayer.STATE_ENDED -> {
//                    if (feedList[position].isSkippable()) {
//                        setPlayerPlayVideo()
//                        feedList[position].setSkippable(false)
//                    } else {
//                        feedList[position].setSkippable(true)
//                        feedList[position].setPlaying(false)
//                        stopPlayer()
//                    }
//                    notifyDataSetChanged()
//                    stateString = "ExoPlayer.STATE_ENDED     -"
//                }
//                else -> stateString = "UNKNOWN_STATE             -"
//            }
//            Log.d(TAG, "changed state to " + stateString
//                    + " playWhenReady: " + playWhenReady)
//        }
//
//        fun onRepeatModeChanged(repeatMode: Int) {
//
//        }
//
//        fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {
//
//        }
//
//        fun onPlayerError(error: ExoPlaybackException) {
//
//        }
//
//        fun onPositionDiscontinuity(reason: Int) {
//
//        }
//
//        fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters) {
//
//        }
//
//        fun onSeekProcessed() {
//
//        }
//    }

    init {
        feedList = myDataset
        commonActions = CommonActions(context)
//        bandwidthMeter = DefaultBandwidthMeter()
//        mediaDataSourceFactory = DefaultDataSourceFactory(context, Util.getUserAgent(context, context.getString(R.string.app_name)), bandwidthMeter as TransferListener<in DataSource>)
//
//        val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(bandwidthMeter)
//
//        trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)
//
//        player = ExoPlayerFactory.newSimpleInstance(context, trackSelector)
//        extractorsFactory = DefaultExtractorsFactory()
//        player!!.addListener(playerLis)
    }

    fun add(position: Int, item: FeedPojo) {
        feedList.add(position, item)
        notifyItemInserted(position)
    }

//    fun playAd(url: String) {
//        val mediaSource = ExtractorMediaSource(Uri.parse(url),
//                mediaDataSourceFactory, extractorsFactory, null, null)
//        player!!.prepare(mediaSource)
//    }

    private fun remove(position: Int) {
        for (pojo in feedList) {
            if (bq_id == pojo.baseFeedId) {
//                pojo.setRepostsCount(pojo.getRepostsCount() - 1)
            }

        }
        feedList.removeAt(position)
        notifyItemRemoved(position)
        Handler().postDelayed({ notifyDataSetChanged() }, 500)
    }


    override fun getItemViewType(position: Int): Int {

        //        if (player != null)
        //            player.stop();
        return feedList[position].tag()!!
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.template_feed, parent, false)
        val rl = v.findViewById<RelativeLayout>(R.id.rl_post_media)
        var layout: View? = null
        when (viewType) {
            0 -> layout = LayoutInflater.from(parent.context).inflate(R.layout.template_post_video, rl, false)
            1 -> layout = LayoutInflater.from(parent.context).inflate(R.layout.template_post_one, rl, false)
            2 -> layout = LayoutInflater.from(parent.context).inflate(R.layout.template_post_two, rl, false)
            3 -> layout = LayoutInflater.from(parent.context).inflate(R.layout.template_post_three, rl, false)
            4 -> layout = LayoutInflater.from(parent.context).inflate(R.layout.template_post_four, rl, false)
            5 -> layout = LayoutInflater.from(parent.context).inflate(R.layout.template_post_five, rl, false)
            else -> layout = LayoutInflater.from(parent.context).inflate(R.layout.template_post_default, rl, false)
        }
        rl.addView(layout)
        return PostViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        val feedPojo = feedList[position]

        val view_holder = holder as PostViewHolder

        if (feedPojo.tag() === 0) {
            //            player.stop();
//            view_holder.progress.visibility = if (feedPojo.isShowProgress()) View.VISIBLE else View.GONE
//            if (feedPojo.isPlaying()) {
//                view_holder.main_media_frame!!.visibility = View.VISIBLE
//                view_holder.iv_video!!.visibility = View.GONE
//                view_holder.iv_post1!!.visibility = View.GONE
//            } else {
//                view_holder.main_media_frame!!.visibility = View.GONE
//                view_holder.iv_video!!.visibility = View.VISIBLE
//                view_holder.iv_post1!!.visibility = View.VISIBLE
//            }
        }


        //        QuickeeCommentsAdapter adapter = new QuickeeCommentsAdapter(feedPojo.getComments(), context);
        //        view_holder.rv_comments.setAdapter(adapter);


        if (view_holder.iv_post1 != null) {


//            setImage(Uri.parse(if (feedPojo.tag!! === 0) feedPojo.getVideoImage() else feedPojo!!.details!![0].url), view_holder.iv_post1, if (feedPojo.tag!! === 0) feedPojo.getVideoThumb() else feedPojo!!.details!![0].thumbUrl!!)
            setImage(Uri.parse(feedPojo!!.details!![0].url), view_holder.iv_post1, feedPojo!!.details!![0].thumbUrl!!)
        }

        if (view_holder.iv_post2 != null) {
            setImage(Uri.parse(feedPojo!!.details!![1].url), view_holder.iv_post2, feedPojo!!.details!![1].thumbUrl!!)
        }

        if (view_holder.iv_post3 != null) {
            setImage(Uri.parse(feedPojo!!.details!![2].url), view_holder.iv_post3, feedPojo!!.details!![2].thumbUrl!!)
        }

        if (view_holder.iv_post4 != null) {
            setImage(Uri.parse(feedPojo!!.details!![3].url), view_holder.iv_post4, feedPojo!!.details!![3].thumbUrl!!)
        }

        if (view_holder.iv_post5 != null) {
            setImage(Uri.parse(feedPojo!!.details!![4].url), view_holder.iv_post5, feedPojo!!.details!![4].thumbUrl!!)
        }

        if (view_holder.tv_more != null) {
            view_holder.tv_more!!.text = "${(feedPojo.tag() - 4)} MORE"
        }

        if (feedPojo.isReposted()) {
            val namelength = feedPojo.getNickname().length
            val parentlength = feedPojo.getParentname().length

            val `is` = ImageSpan(context, R.drawable.ic_share)
            val ss = SpannableString(feedPojo.getNickname() + "  Reposted " + feedPojo.getParentname() + "'s Quickee")
            ss.setSpan(`is`, namelength + 1, namelength + 2, 0)
            val nickname = object : ClickableSpan() {
                override fun onClick(textView: View) {

                    if (m_user_id != feedPojo.userId) {
                        stopPlayer()
//                        context.startActivity(Intent(context, Test::class.java).putExtra("id", feedPojo.userId))
                    }
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false
                }
            }
            val parentName = object : ClickableSpan() {
                override fun onClick(textView: View) {

                    if (m_user_id != feedPojo.baseUserId) {
                        stopPlayer()
//                        context.startActivity(Intent(context, Test::class.java).putExtra("id", feedPojo.baseUserId))
                    }
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false
                }
            }
            ss.setSpan(nickname, 0, namelength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            ss.setSpan(parentName, namelength + 10, namelength + 10 + parentlength + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            val textView = view_holder.tv_feedername
            view_holder.tv_feedername.setTextColor(context.resources.getColor(R.color.black))
            textView.text = ss
            textView.movementMethod = LinkMovementMethod.getInstance()
            textView.highlightColor = Color.TRANSPARENT

        } else if (feedPojo.isReposted()) {
            val namelength = feedPojo.getNickname().length
            val parentlength = feedPojo.getParentname().length

            val `is` = ImageSpan(context, R.drawable.ic_share)
            val ss = SpannableString(feedPojo.getNickname() + "  Reposted " + feedPojo.getParentname() + "'s Quickee")
            ss.setSpan(`is`, namelength + 1, namelength + 2, 0)
            val nickname = object : ClickableSpan() {
                override fun onClick(textView: View) {

                    if (m_user_id != feedPojo.userId) {
                        stopPlayer()
//                        context.startActivity(Intent(context, Test::class.java).putExtra("id", feedPojo.userId))
                    }
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false
                }
            }
            val parentName = object : ClickableSpan() {
                override fun onClick(textView: View) {

                    if (m_user_id != feedPojo.baseUserId) {
                        stopPlayer()
//                        context.startActivity(Intent(context, Test::class.java).putExtra("id", feedPojo.baseUserId))
                    }
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false
                }
            }
            ss.setSpan(nickname, 0, namelength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            ss.setSpan(parentName, namelength + 10, namelength + 10 + parentlength + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            val textView = view_holder.tv_feedername
            view_holder.tv_feedername.setTextColor(context.resources.getColor(R.color.black))
            textView.text = ss
            textView.movementMethod = LinkMovementMethod.getInstance()
            textView.highlightColor = Color.TRANSPARENT

        } else {
            view_holder.tv_feedername.setText(feedPojo.getNickname())
            view_holder.tv_feedername.setOnClickListener {
                if (m_user_id != feedPojo.userId) {
                    stopPlayer()
//                    context.startActivity(Intent(context, Test::class.java).putExtra("id", feedPojo.userId))
                }
            }
            view_holder.tv_feedername.setTextColor(context.resources.getColor(R.color.colorPrimary))
        }
        view_holder.tv_feedText.setText(feedPojo.text)
        view_holder.tv_feederinfo.setText(feedPojo.createdDate)
        view_holder.tv_feedLikes.text = commonActions.textToKM(Integer.valueOf(feedPojo.likesCount).toLong())
//        view_holder.tv_repost.text = if (feedPojo.getRepostsCount() > 0) commonActions.textToKM(Integer.valueOf(feedPojo.getRepostsCount()).toLong()) else "Repost"
        view_holder.iv_feedLikes.setImageDrawable(context.resources.getDrawable(if (feedPojo.isFav()) R.drawable.ic_like else R.drawable.ic_unlike))
//        view_holder.tv_feedLikes.setTextColor(context.resources.getColor(if (feedPojo.isFav()) R.color.theme_pink else R.color.gray))

        view_holder.iv_feedLikes.setOnClickListener {
            bq_id = feedPojo.baseFeedId
            q_id = feedPojo.feedId
            val likeCnt: Int = feedPojo.likesCount
            if (feedPojo.isFav()) {
                feedPojo.setFav(false)
                if (likeCnt > 0) {
                    feedPojo!!.likesCount = (likeCnt!! - 1)
                }
                if (isFav)
                    remove(view_holder.adapterPosition)
            } else {
                feedPojo.setFav(true)
                feedPojo!!.likesCount = (likeCnt!! + 1)

            }
//            for (pojo in feedList) {
//                if (bq_id == pojo.baseFeedId) {
//                    pojo.setFav(feedPojo.isFav())
//                    pojo!!.likesCount!!=(feedPojo.likesCount)
//                }
//
//            }
            notifyDataSetChanged()
            FunctionTask(0)
        }
        view_holder.tv_feedLikes.setOnClickListener {
            bq_id = feedPojo.baseFeedId
            val passport_data = Bundle()
            passport_data.putString("id", bq_id.toString() + "")
            passport_data.putInt("whichFunction", 0)
            stopPlayer()
            val friends = Intent(context, Test::class.java)
            friends.putExtra("profile", passport_data)
            context.startActivity(friends)
        }
        view_holder.tv_repost.setOnClickListener { view ->
            bq_id = feedPojo.baseFeedId
            val passport_data = Bundle()
            passport_data.putString("id", bq_id.toString() + "")
            passport_data.putInt("whichFunction", 1)
            stopPlayer()
            val friends = Intent(context, Test::class.java)
            friends.putExtra("profile", passport_data)
            context.startActivity(friends)
        }
        view_holder.iv_repost.setOnClickListener { v ->
            val alertDialogBuilder = AlertDialog.Builder(context, R.style.AlertDialogCustom)
            alertDialogBuilder.setMessage("Are you sure to Repost this Quickee?")
            alertDialogBuilder.setCancelable(false)
                    .setPositiveButton("YES") { dialog, id ->
                        bq_id = feedPojo.baseFeedId
                        q_id = feedPojo.feedId
//                        val count = feedPojo.getRepostsCount()
//                        feedPojo.setRepostsCount(count + 1)
//                        for (pojo in feedList) {
//                            if (bq_id == pojo.baseFeedId) {
//                                pojo.setRepostsCount(feedPojo.getRepostsCount())
//                            }
//
//                        }
                        notifyDataSetChanged()
                        FunctionTask(1)
                        dialog.dismiss()
                    }.setNegativeButton("CANCEL") { dialog, id -> dialog.dismiss() }.show()
        }
        view_holder.tv_feedComments.text = commonActions.textToKM(Integer.valueOf(feedPojo.commentCount).toLong())
        view_holder.iv_userLogo.setImageURI(Uri.parse(feedPojo.profileImg))
        view_holder.iv_userLogo.setOnClickListener { v ->
            stopPlayer()
            context.startActivity(Intent(context, Test::class.java).putExtra("id", feedPojo.userId))

        }
        view_holder.tv_feedComments.setOnClickListener { arg0 ->
            // TODO Auto-generated method stub
//            SingletonHolder.getInstance().setPostPojo(feedPojo)
            bq_id = feedPojo.feedId
            stopPlayer()
            val comment = Intent(context, if (feedPojo.tag() === 0) Test::class.java else Test::class.java)
            comment.putExtra("id", bq_id)
            context.startActivity(comment)

            //                ((Activity)context).overridePendingTransition(R.anim.slide_in_up,R.anim.fake);
        }
        view_holder.iv_overFlow.setOnClickListener { arg0 ->
            // TODO Auto-generated method stub
            val popup = PopupMenu(context, view_holder.iv_overFlow)
            //inflating menu from xml resource
            popup.inflate(R.menu.comments_menu)
            popup.menu.findItem(R.id.menu_edit).isVisible = false
//            if (feedPojo.userId === HomeActivity.userProfilePojo.userId) {
//                popup.menu.findItem(R.id.menu_remove).isVisible = true
//                popup.menu.findItem(R.id.menu_report).isVisible = false
//            } else {
//                popup.menu.findItem(R.id.menu_remove).isVisible = false
//                popup.menu.findItem(R.id.menu_report).isVisible = true
//            }
            //adding click listener
            popup.setOnMenuItemClickListener { item ->
                bq_id = feedPojo.baseFeedId
                q_id = feedPojo.feedId
                when (item.itemId) {
                    R.id.menu_edit -> {
                    }
                    R.id.menu_remove -> {
                        val alertDialogBuilder = AlertDialog.Builder(context, R.style.AlertDialogCustom)
                        alertDialogBuilder.setMessage("Are you sure to Delete this Quickee?")
                        alertDialogBuilder.setCancelable(false)
                                .setPositiveButton("YES") { dialog, id ->
                                    remove(view_holder.adapterPosition)
                                    FunctionTask(2)
                                    dialog.dismiss()
                                }.setNegativeButton("CANCEL") { dialog, id -> dialog.dismiss() }.show()
                    }
                    R.id.menu_report -> FunctionTask(3)
                }//handle menu1 click
                false
            }
            //displaying the popup
            popup.show()
        }

    }

    fun setImage(ori: Uri, view: SimpleDraweeView?, thumb: String) {

        //        DraweeController controller = Fresco.newDraweeControllerBuilder()
        //                .setUri(Uri.parse())
        //                .setAutoPlayAnimations(true)
        //                .build();
        //        view.setController(controller);

        val controller = Fresco.newDraweeControllerBuilder()
                .setLowResImageRequest(ImageRequest.fromUri(Uri.parse("https://seeklogo.com/images/A/Audi-logo-ED84DFE2E3-seeklogo.com.png")))
                .setImageRequest(ImageRequest.fromUri("https://www.autocar.co.uk/sites/autocar.co.uk/files/styles/gallery_slide/public/audi-rs7_1.jpg?itok=IbKvRTO6"))
                .setOldController(view!!.controller)
                .build()

        //        Fresco.getImagePipeline().prefetchToBitmapCache(
        //                ImageRequest.fromUri(ori), view.getContext());

        view.controller = controller

        //        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(ori)
        //                .setResizeOptions(new ResizeOptions(75, 75))
        //                .build();
        //        view.setController(Fresco.newDraweeControllerBuilder()
        //                .setLowResImageRequest(ImageRequest.fromUri(Uri.parse(thumb)))
        //                .setOldController(view.getController())
        //                .setImageRequest(ImageRequest.fromUri(ori)).build());

        //        DraweeController ctrl = Fresco.newDraweeControllerBuilder()
        //                .setUri(ori)
        //                .setOldController(view.getController())
        //                .build();
        //
        //        view.setController(ctrl);
        //        view.setImageURI(ori);

    }

    private fun FunctionTask(type: Int) {
//        WebServices(context).function(bq_id, q_id, type, this)
    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return feedList.size
    }

    fun checkSingleTon() {

//        val feedPojo = SingletonHolder.getInstance().getPostPojo()
//        if (feedPojo != null)
//            for (i in feedList.indices) {
//
//                if (feedPojo!!.feedId === feedList[i].feedId) {
//                    if (feedPojo!!.isRemoved()) {
//                        remove(i)
//                    } else {
//                        feedList[i].setFav(feedPojo!!.isFav())
//                        feedList[i]!!.likesCount!!=(feedPojo!!.likesCount)
//                        feedList[i].setCommentsCount(feedPojo!!.commentCount)
//                        feedList[i].setRepostsCount(feedPojo!!.getRepostsCount())
//                        notifyItemChanged(i)
//                    }
//                }
//            }
    }

    fun stopPlayer(pos: Int) {
        if (pos >= 0) {
//            feedList[pos].setPlaying(false)
//            if (player != null)
//                player!!.stop()
            notifyDataSetChanged()
        }
    }

    fun stopPlayer() {

//        if (player != null) {
//            player!!.stop()
//            player!!.release()
//            player = null
//        }

    }

    inner class PostViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        internal var iv_userLogo: SimpleDraweeView
        internal var iv_overFlow: ImageView
        internal var iv_feedLikes: ImageView
        internal var iv_repost: ImageView
        internal var rl_post_media: RelativeLayout? = null
        //        RecyclerView rv_comments;
        internal var tv_feedername: TextView
        internal var tv_feederinfo: TextView
        internal var tv_feedText: TextView
        internal var tv_feedComments: TextView
        internal var tv_feedLikes: TextView
        internal var tv_repost: TextView
        internal var tv_more: TextView? = null
        internal var tv_share: TextView? = null
        internal var iv_post1: SimpleDraweeView? = null
        internal var iv_post2: SimpleDraweeView? = null
        internal var iv_post3: SimpleDraweeView? = null
        internal var iv_post4: SimpleDraweeView? = null
        internal var iv_post5: SimpleDraweeView? = null
        //        internal var simpleExoPlayerView: SimpleExoPlayerView
        internal var iv_video: ImageView? = null
        internal var mFullScreenDialog: Dialog? = null
        internal var mFullScreenIcon: View? = null
        internal var progress: ProgressBar? = null
        internal var main_media_frame: FrameLayout? = null

        internal var feedPojo: FeedPojo? = null

        init {
            iv_userLogo = view.findViewById(R.id.iv_userLogo)
            rl_post_media = view.findViewById(R.id.rl_post_media)
            iv_overFlow = view.findViewById(R.id.iv_overFlow)
            iv_feedLikes = view.findViewById(R.id.iv_feedLikes)
            //            rv_comments = view.findViewById(R.id.rv_comments);
            iv_repost = view.findViewById(R.id.iv_share)
            tv_feedername = view.findViewById(R.id.tv_feederName)
            tv_feederinfo = view.findViewById(R.id.tv_feedTime)
            tv_feedText = view.findViewById(R.id.tv_feedText)
            tv_repost = view.findViewById(R.id.tv_share)
            tv_feedLikes = view.findViewById(R.id.tv_feedLikes)
            tv_feedComments = view.findViewById(R.id.tv_feedComments)
            initView(view)

        }

        private fun initView(layout: View) {
            iv_post1 = layout.findViewById(R.id.iv_post1)
            iv_post2 = layout.findViewById(R.id.iv_post2)
            iv_post3 = layout.findViewById(R.id.iv_post3)
            iv_post4 = layout.findViewById(R.id.iv_post4)
            iv_post5 = layout.findViewById(R.id.iv_post5)
            iv_video = layout.findViewById(R.id.iv_video)
            tv_more = layout.findViewById(R.id.tv_more)
            tv_share = layout.findViewById(R.id.tv_repost)
//            simpleExoPlayerView = layout.findViewById<View>(R.id.exoplayer)
            main_media_frame = layout.findViewById(R.id.main_media_frame)

            progress = layout.findViewById(R.id.progress)
            //            mFullScreenDialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen) {
            //                public void onBackPressed() {
            //                    if (mExoPlayerFullscreen) {
            //                        closeFullscreenDialog(simpleExoPlayerView);
            //                        super.onBackPressed();
            //                    }
            //                }
            //            };


            if (iv_video != null) {
                iv_video!!.setOnClickListener(this)
            }

            if (mFullScreenIcon != null) {
                mFullScreenIcon!!.setOnClickListener(this)
            }


            if (tv_share != null) {
                tv_share!!.setOnClickListener(this)
            }


            if (main_media_frame != null) {
                main_media_frame!!.setOnClickListener(this)
            }

            if (rl_post_media != null) {
                rl_post_media!!.setOnClickListener(this)
            }
            if (iv_post1 != null) {
                iv_post1!!.setOnClickListener(this)
            }


        }

        override fun onClick(view: View) {
            if (adapterPosition != -1)
                feedPojo = feedList[adapterPosition]
            when (view.id) {
                R.id.iv_video -> {
                    //                    if(player!=null)
                    //                        player.stop();
                    //                    position = getAdapterPosition();
                    //                    feedPojo = feedList.get(position);
                    //                    feedList.get(position).setSkippable(true);
                    //                    feedList.get(position).setPlaying(true);
                    //                    for (int i = 0; i < feedList.size(); i++) {
                    //                        if (i != position) {
                    //                            feedList.get(i).setSkippable(false);
                    //                            feedList.get(i).setPlaying(false);
                    //                        }
                    //                    }
                    //                    selectedVideoUrl = feedPojo.getVideoUrl();
                    ////                    tv_skip_ad.setVisibility(View.VISIBLE);
                    //
                    //                    getAdUrl(feedPojo.userId);
                    //                    simpleExoPlayerView.setPlayer(player);
                    ////                    iv_video.setVisibility(View.INVISIBLE);
                    ////                    iv_post1.setVisibility(View.INVISIBLE);
                    ////                    main_media_frame.setVisibility(View.VISIBLE);
                    ////                    simpleExoPlayerView.setUseController(false);
                    ////                    simpleExoPlayerView.showController();
                    //                    if (player != null)
                    //                        player.setPlayWhenReady(true);
                    //                    simpleExoPlayerView.setUseController(false);
                    //                    simpleExoPlayerView.setShutterBackgroundColor(Color.TRANSPARENT);
                    //                    notifyDataSetChanged();


                    //if Video failed
//                    SingletonHolder.getInstance().setPostPojo(feedPojo)
//                    context.startActivity(Intent(context, QuickeeDetailActivity::class.java))

                }
                //                case R.id.exo_fullscreen_icon: {
                //                    closeFullscreenDialog(simpleExoPlayerView);
                //                }

                //                break;
                R.id.rl_post_media -> {
                    //                    if (feedPojo.tag!! != 0) {
                    //                        SingletonHolder.getInstance().setPostPojo(feedPojo);
                    //                        if (player != null)
                    //                            player.stop();
                    //                    context.startActivity(new Intent(context, QuickeeDetailActivity.class).putExtra("quickeeId", feedPojo.feedId));
                    //                        context.startActivity(new Intent(context, QuickeeDetailActivity.class));
                    //                    }

                    //if Video failed
//                    SingletonHolder.getInstance().setPostPojo(feedPojo)
//                    context.startActivity(Intent(context, QuickeeDetailActivity::class.java))
                }
                R.id.iv_post1 -> {
                    //                    SingletonHolder.getInstance().setPostPojo(feedPojo);
                    //                    stopPlayer();
                    //                    context.startActivity(new Intent(context, QuickeeDetailActivity.class).putExtra("quickeeId", feedPojo.feedId));
                    //                    context.startActivity(new Intent(context, QuickeeDetailActivity.class));

                    //if Video failed
//                    SingletonHolder.getInstance().setPostPojo(feedPojo)
//                    context.startActivity(Intent(context, QuickeeDetailActivity::class.java))
                }
                R.id.main_media_frame -> {
                    //                    SingletonHolder.getInstance().setPostPojo(feedPojo);
                    //                    if (player != null)
                    //                        player.stop();
                    //                    context.startActivity(new Intent(context, QuickeeDetailActivity.class).putExtra("id", feedPojo.feedId));
                    //                    context.startActivity(new Intent(context, QuickeeDetailActivity.class));

                    //                    openFullscreenDialog(simpleExoPlayerView);

                    //if Video failed
//                    SingletonHolder.getInstance().setPostPojo(feedPojo)
//                    context.startActivity(Intent(context, QuickeeDetailActivity::class.java))
                }
                R.id.tv_repost -> {
                    //                    FacebookSdk.sdkInitialize(context);
                    //                    shareDialog = new ShareDialog((Activity) context);
                    //                    final Dialog dialog = new Dialog(context);
                    //                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    //                    view = ((Activity) context).getLayoutInflater().inflate(R.layout.dialog_share, null);
                    //                    view.findViewById(R.id.tv_share_fb).setOnClickListener(new View.OnClickListener() {
                    //                        @Override
                    //                        public void onClick(View view) {
                    //                            dialog.dismiss();
                    //                            try {
                    //                                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    //                                    ShareLinkContent content = new ShareLinkContent.Builder()
                    //                                            .setContentUrl(Uri.parse(feedPojo.getVideoImage()))
                    //                                            .build();
                    //
                    //                                    shareDialog.show(content);
                    //                                }
                    //                            } catch (Exception e) {
                    //                                new CommonActions(context).showToast("No Facebook App Installed in device");
                    //                                Log.v("QM", "Exception while sending image on facebook" + " " + e.getMessage());
                    //                            }
                    //                        }
                    //                    });
                    //                    view.findViewById(R.id.tv_share_twit).setOnClickListener(new View.OnClickListener() {
                    //                        @Override
                    //                        public void onClick(View view) {
                    //                        }
                    //                    });
                    //                    view.findViewById(R.id.tv_share_gp).setOnClickListener(new View.OnClickListener() {
                    //                        @Override
                    //                        public void onClick(View view) {
                    //                        }
                    //                    });
                    //                    view.findViewById(R.id.tv_share_pin).setOnClickListener(new View.OnClickListener() {
                    //                        @Override
                    //                        public void onClick(View view) {
                    //                        }
                    //                    });
                    //                    view.findViewById(R.id.tv_share_lin).setOnClickListener(new View.OnClickListener() {
                    //                        @Override
                    //                        public void onClick(View view) {
                    //                        }
                    //                    });
                    //                    dialog.setContentView(view);
                    //                    dialog.setCancelable(false);
                    //                    dialog.show();
//                    var contentDetails = if (feedPojo.tag!! === 0) feedPojo.getVideoUrl() else feedPojo.getImages().get(0].url

                    var contentDetails = "\nQuickeeme - Real Time Video Updates \n\nHey please check this application " + "https://play.google.com/store/apps/details?id=" + context.packageName
                    val share = Intent(Intent.ACTION_SEND)
                    share.type = "text/plain"
                    share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)

                    // Add data to the intent, the receiving app will decide
                    // what to do with it.

                    share.putExtra(Intent.EXTRA_TITLE, "Quickeeme - Real Time Video Updates")
                    share.putExtra(Intent.EXTRA_SUBJECT, "Quickeeme - Real Time Video Updates")
//                    share.putExtra(Intent.EXTRA_TEXT, "Quickee Title : " + feedPojo.getTitle() + "\n\nhttps://quickeeme.com/p/" + feedPojo.feedId + contentDetails)

                    context.startActivity(Intent.createChooser(share, "Share link!"))
                }
            }

        }

//        private fun openFullscreenDialog(playerView: SimpleExoPlayerView) {
//
//
//            playerView.setUseController(true)
//            //            selectedSimpleExoPlayerView.setControllerHideOnTouch(true);
//            //            selectedSimpleExoPlayerView.showController();
//            fl_exoPlayer.visibility = View.VISIBLE
//            (playerView.getParent() as ViewGroup).removeView(playerView)
//            mFullScreenDialog!!.addContentView(playerView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
//            mFullScreenIcon!!.setBackgroundDrawable(context.resources.getDrawable(R.drawable.fullscreen_shrink))
//            mExoPlayerFullscreen = true
//            mFullScreenDialog!!.show()
//        }


        //        private void closeFullscreenDialog(SimpleExoPlayerView playerView) {
        //            fl_exoPlayer.setVisibility(View.INVISIBLE);
        //            ((ViewGroup) playerView.getParent()).removeView(playerView);
        //            main_media_frame.addView(playerView);
        //            main_media_frame.setOnClickListener(this);
        //            main_media_frame.setVisibility(View.VISIBLE);
        //            mExoPlayerFullscreen = false;
        //            mFullScreenDialog.dismiss();
        //            mFullScreenIcon.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.fullscreen_expand));
        //        }


    }


//    private fun setPlayerPlayVideo() {
//        feedList[position].setSkippable(false)
//        val mediaSource = ExtractorMediaSource(Uri.parse(selectedVideoUrl),
//                mediaDataSourceFactory, extractorsFactory, null, null)
//        player!!.prepare(mediaSource)
//
//    }


}