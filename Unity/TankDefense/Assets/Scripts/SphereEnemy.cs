
public class SphereEnemy : EnemyBase {
    public override void Start() {
        base.Start();
        this.HP = GameConfig.sphereEnemyHP;
        this.moveSpeed = GameConfig.enemySpeed;
        this.destoryCoins = GameConfig.sphereEnemyCoins;
    }

    public override void Update() {
        base.Update();

    }
}
