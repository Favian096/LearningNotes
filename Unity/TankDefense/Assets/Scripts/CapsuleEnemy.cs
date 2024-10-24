
public class CapsuleEnemy : EnemyBase {

    public override void Start() {
        base.Start();
        this.HP = GameConfig.cubeEnemyHP;
        this.moveSpeed = GameConfig.enemySpeed;
        this.destoryCoins = GameConfig.capsuleEnemyCoins;
    }

    public override void Update() {
        base.Update();
    }

}
