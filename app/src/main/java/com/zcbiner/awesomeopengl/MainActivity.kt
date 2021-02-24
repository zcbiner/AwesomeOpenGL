package com.zcbiner.awesomeopengl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        val DEMO_TEXT: Array<String> = arrayOf(
            "简单几何图形",
            "高斯模糊",
            "水波纹效果",
            "粒子效果",
            "3D模型效果"
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        initView()
    }

    private fun initView() {
        recyclerView.adapter = RvAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    inner class RvAdapter : RecyclerView.Adapter<RvViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
            val itemView = LayoutInflater.from(this@MainActivity).inflate(R.layout.view_rv_item, parent, false)
            return RvViewHolder(itemView)
        }

        override fun getItemCount(): Int = DEMO_TEXT.size

        override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
            holder.tvContent.text = DEMO_TEXT[position]
        }

    }

    inner class RvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvContent: TextView = itemView.findViewById(R.id.tvContent)
    }
}