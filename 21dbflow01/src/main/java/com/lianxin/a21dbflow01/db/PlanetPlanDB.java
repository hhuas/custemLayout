package com.lianxin.a21dbflow01.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * FileName: PlanetPlanDB
 * Author:   花恩成
 * Date:     2019/9/27 16:18
 * Description: db的数据库
 */

@Database(name = PlanetPlanDB.name, version = PlanetPlanDB.version)
public class PlanetPlanDB {
    public static final String name = "planetplan";
    public static final int version = 1;
}
