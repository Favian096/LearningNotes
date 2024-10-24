using UnityEngine;

public class GameConfig : MonoBehaviour {
    //游戏是否开始
    public static bool isStart = true;
    //主炮台生命数
    public static int mainTankHP = 3;
    //主炮台碰到敌人后减少的生命数
    public static int mainTankReduce = 1;
    //每隔多久产生一个敌人
    public static float enemyGenerateInterval = 1.0f;
    //boss敌人生命点数
    public static float bossEnemyHP = 50.0f;
    //Cube敌人生命点数
    public static float cubeEnemyHP = 12.0f;
    //Capsule敌人生命点数
    public static float capsuleEnemyHP = 15.0f;
    //Cylinder敌人生命点数
    public static float cylinderEnemyHP = 15.0f;
    //Sphere敌人生命点数
    public static float sphereEnemyHP = 8.0f;
    //boss敌人消灭掉落金币
    public static int bossEnemyCoins = 50;
    //Cube敌人消灭掉落金币
    public static int cubeEnemyCoins = 20;
    //Capsule敌人消灭掉落金币数
    public static int capsuleEnemyCoins = 30;
    //Cylinder敌人消灭掉落金币数
    public static int cylinderEnemyCoins = 30;
    //Sphere敌人消灭掉落金币数
    public static int sphereEnemyCoins = 10;
    //敌人的移动速度
    public static float enemySpeed = 8.0f;
    //子弹速度
    public static float bulletSpeed = 32.0f;
    //子弹伤害
    public static float bulletDamage = 1.0f;
    //地雷伤害
    public static float landmineDamage = 5.0f;
    // 炮台射速(发射子弹间隔时间s)
    public static float fireSpeed = 0.1f;
    //射线检测距离
    public static float laserRange = 20.0f;
    //激光开始宽度
    public static float laserStartWidth = 0.2f;
    //激光结束宽度
    public static float laserEndWidth = 0.1f;
    //激光伤害
    public static float laserDamage = 0.01f;
    //激光能量槽
    public static float laserEnergy = 10.0f;
    //激光能量槽单位时间衰减量
    public static float laserEnergyReduce = 0.01f;
    //激光能量槽单位时间内恢复量(0~1)
    public static float laserEnergyRestore = 0.25f;
    //激光延迟恢复时间s
    public static float laserEnergyRestoreDalayTime = 1.0f;
    //当前金币数量
    public static int goldCoin = 0;
    //消灭的敌人数目
    public static int destoryEnemyNum = 0;
    //对敌人造成的总伤害
    public static float destoryEnemyDamage = 0;
    //商店Tank价格
    public static int tankPrice = 200;
    //商店landmine价格
    public static int landminePrice = 50;
    //炮台检测距离
    public static float fortDetectRange = 0.01f;
    //炮塔距离多少变幻到炮台位置
    public static float tankDetectRange = 1.0f;
    //landmine爆炸范围(范围内敌人会sufferDamage)
    public static float landmineExplosionRange = 8.0f;
    //landmine磁吸检测范围
    public static float landmineDetectEnemyRange = 8.0f;
    //landmine距离多少变换到路径上
    public static float landmineDetectRange = 0.5f;
    //距离mainTank多近不可放置landmine
    public static float awayFromMainTank = 1.0f;
    //landmine磁吸移动速度
    public static float landmineSpeed = 4.0f;
    //每消灭多少个敌人boss出现
    public static int generateBoss = 10;












    public static Color getRandomColor() {
        return Color.HSVToRGB(Random.Range(0f, 1f), 0.7f, 1.0f);
    }

}
