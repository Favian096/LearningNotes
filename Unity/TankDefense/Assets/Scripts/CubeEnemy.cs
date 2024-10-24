
public class CubeEnemy : EnemyBase {
    public override void Start() {
        base.Start();
        this.HP = GameConfig.cubeEnemyHP;
        this.moveSpeed = GameConfig.enemySpeed;
        this.destoryCoins = GameConfig.cubeEnemyCoins;
    }

    public override void Update() {
        base.Update();

    }
}
