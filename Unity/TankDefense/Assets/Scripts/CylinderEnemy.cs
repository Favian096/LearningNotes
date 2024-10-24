
public class CylinderEnemy : EnemyBase {
    public override void Start() {
        base.Start();
        this.HP = GameConfig.cylinderEnemyHP;
        this.moveSpeed = GameConfig.enemySpeed;
        this.destoryCoins = GameConfig.cylinderEnemyCoins;
    }

    public override void Update() {
        base.Update();

    }
}
