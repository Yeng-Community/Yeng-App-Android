package `in`.yeng.user.newsupdates.helpers

import `in`.yeng.user.R
import `in`.yeng.user.helpers.AnimUtil
import `in`.yeng.user.helpers.viewbinders.BinderTypes
import `in`.yeng.user.team.TeamMembersActivity
import `in`.yeng.user.team.dom.Group
import `in`.yeng.user.team.dom.Profile
import `in`.yeng.user.team.dom.Team
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import jp.satorufujiwara.binder.recycler.RecyclerBinder
import kotlinx.android.synthetic.main.main_content.view.*
import kotlinx.android.synthetic.main.team_card.view.*
import kotlinx.android.synthetic.main.team_group_card.view.*
import org.jetbrains.anko.intentFor


class GroupAdaptor(activity: AppCompatActivity, val data: Group) : RecyclerBinder<BinderTypes>(activity, BinderTypes.TYPE_TEAM_PROFILES) {

    override fun layoutResId(): Int = R.layout.team_group_card

    override fun onCreateViewHolder(v: View): ViewHolder = ViewHolder(v)


    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val holder = viewHolder as ViewHolder
        with(holder.view) {
            group_name.text = data.group
            Glide.with(activity).load("https://picsum.photos/300/300/?random").into(group_image)

            findViewById<View>(R.id.card).setOnClickListener {
                AnimUtil.clickAnimation(this)
                activity.startActivity(activity.intentFor<TeamMembersActivity>("name" to data.group, "id" to data.id))
            }
        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

}