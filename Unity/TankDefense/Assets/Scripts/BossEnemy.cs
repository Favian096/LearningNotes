using UnityEngine;

public class BossEnemy : EnemyBase {
    public override void Start() {
        base.Start();
        this.HPSliderCanvas.transform.position = new Vector3(
            this.transform.position.x,
            this.transform.position.y * 10.0f,
            this.transform.position.z);

        Color color = this.gameObject.GetComponent<Renderer>().material.color;
        this.transform.GetChild(0).GetComponent<Renderer>().material.color = color;
        this.transform.GetChild(0).transform.GetChild(0).GetComponent<Renderer>().material.color = color;
        this.transform.GetChild(1).GetComponent<Renderer>().material.color = color;

        this.HP = GameConfig.bossEnemyHP;
        this.moveSpeed = GameConfig.enemySpeed;
        this.destoryCoins = GameConfig.bossEnemyCoins;
        this.mainTankLifeReduce *= 2;
    }

    public override void Update() {
        base.Update();

    }
}
