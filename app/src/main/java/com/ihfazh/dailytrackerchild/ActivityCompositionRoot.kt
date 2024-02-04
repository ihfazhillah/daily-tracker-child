package com.ihfazh.dailytrackerchild

import android.app.Application
import android.content.Context
import com.ihfazh.dailytrackerchild.remote.DummyClient
import com.ihfazh.dailytrackerchild.utils.ChildrenCache
import com.ihfazh.dailytrackerchild.utils.DateProvider

class ActivityCompositionRoot(context: Application) {
    val childrenCache: ChildrenCache = ChildrenCache(context.getSharedPreferences("childrenCache", Context.MODE_PRIVATE))
    val client: DummyClient = DummyClient()
    val dateProvider = DateProvider()
}