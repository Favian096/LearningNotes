using TMPro;
using Unity.VisualScripting;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;
public class GameSceneManager : MonoBehaviour {
    public GameObject enemyGenerate;
    public AudioSource backgoundMusic;
    public AudioSource audioSource;
    public GameObject tankGenerate;
    public GameObject tank;
    public GameObject landmine;
    public GameObject landmineGenerate;
    public AudioClip clickAudio;
    public AudioClip gameOverAudio;
    public TextMeshProUGUI Title;
    public TextMeshProUGUI mainTankLifesText;
    public TextMeshProUGUI shopTitle;
    public Button buyTankButton;
    public TextMeshProUGUI buyTankButtonText;
    public Button buyLandmineButton;
    public TextMeshProUGUI buyLandmineButtonText;
    public EnemyBase cubeEnemy;
    public EnemyBase cylinderEnemy;
    public EnemyBase capsuleEnemy;
    public EnemyBase sphereEnemy;
    public EnemyBase bossEnemy;
    private float enemyGenerateTimeTemp = 0;
    private int originLifes;
    private int generateBoss;
    private float originLaserDamage;
    private float originLandmineDamage;
    private float originBossEnemyHP;
    public GameObject gameOverPlane;
    public TextMeshProUGUI totalDamage;
    public TextMeshProUGUI totalDestory;
    private bool isGameOver;

    void Start() {
        this.buyLandmineButtonText.text = "landmine x " + GameConfig.landminePrice;
        this.buyTankButtonText.text = "tank x " + GameConfig.tankPrice;
        this.originLifes = GameConfig.mainTankHP;
        this.generateBoss = GameConfig.generateBoss;
        this.originLandmineDamage = GameConfig.landmineDamage;
        this.originLaserDamage = GameConfig.laserDamage;
        this.originBossEnemyHP = GameConfig.bossEnemyHP;
        Instantiate(this.tank, this.tankGenerate.transform);
        Instantiate(this.landmine, this.landmineGenerate.transform);
        this.isGameOver = false;
        this.gameOverPlane.SetActive(this.isGameOver);
        this.backgoundMusic.Play();
    }

    void Update() {
        this.shopTitle.text = "Gold coins: " + GameConfig.goldCoin;

        this.mainTankLifesText.text = "LIFES x " + GameConfig.mainTankHP;

        if (GameConfig.destoryEnemyNum > 0) {
            this.Title.text = "Destory Enemy x " + GameConfig.destoryEnemyNum;
        }

        if (GameConfig.goldCoin >= GameConfig.tankPrice) {
            this.buyTankButton.interactable = true;
        } else {
            this.buyTankButton.interactable = false;
        }

        if (GameConfig.goldCoin >= GameConfig.landminePrice) {
            this.buyLandmineButton.interactable = true;
        } else {
            this.buyLandmineButton.interactable = false;
        }

        this.enemyGenerateTimeTemp += Time.deltaTime;
        if (this.enemyGenerateTimeTemp > GameConfig.enemyGenerateInterval) {
            this.enemyGenerateTimeTemp = 0;
            int randomEnemy = Random.Range(0, 3);
            if (GameConfig.destoryEnemyNum >= this.generateBoss) {
                randomEnemy = 4;
                this.generateBoss += GameConfig.generateBoss;
                GameConfig.bossEnemyHP += 50;
                GameConfig.landmineDamage = GameConfig.bossEnemyHP * 0.2f;
                GameConfig.laserDamage += this.originLaserDamage;
            }
            switch (randomEnemy) {
                case 0:
                    Instantiate(this.cubeEnemy, this.enemyGenerate.transform);
                    break;
                case 1:
                    Instantiate(this.cylinderEnemy, this.enemyGenerate.transform);
                    break;
                case 2:
                    Instantiate(this.capsuleEnemy, this.enemyGenerate.transform);
                    break;
                case 3:
                    Instantiate(this.sphereEnemy, this.enemyGenerate.transform);
                    break;
                default:
                    Instantiate(this.bossEnemy, this.enemyGenerate.transform);
                    break;
            }
        }



        if (GameConfig.mainTankHP <= 0 && !this.isGameOver) {
            this.backgoundMusic.Stop();
            this.isGameOver = true;
            this.audioSource.clip = this.gameOverAudio;
            this.audioSource.Play();
            Time.timeScale = 0;
            this.gameOverPlane.SetActive(this.isGameOver);
            this.totalDamage.text = "Total damage dealt : " + GameConfig.destoryEnemyDamage.ToString("f2");
            this.totalDestory.text = "Destory enemy : " + GameConfig.destoryEnemyNum;
        }

    }

    public void onBuyTankButtonClick() {
        if (GameConfig.goldCoin >= GameConfig.tankPrice) {
            GameConfig.goldCoin -= GameConfig.tankPrice;
            Instantiate(this.tank, this.tankGenerate.transform);
            this.audioSource.clip = this.clickAudio;
            this.audioSource.Play();
        }
    }

    public void onBuyLandmineButtonClick() {
        if (GameConfig.goldCoin >= GameConfig.landminePrice) {
            GameConfig.goldCoin -= GameConfig.landminePrice;
            Instantiate(this.landmine, this.landmineGenerate.transform);
            this.audioSource.clip = this.clickAudio;
            this.audioSource.Play();
        }
    }

    public void onContinueButtonClick() {
        this.resetData();
        this.audioSource.clip = this.clickAudio;
        this.audioSource.Play();
        SceneManager.LoadScene(0);

    }

    public void onRestartButtonClick() {
        this.resetData();
        this.audioSource.clip = this.clickAudio;
        this.audioSource.Play();
        SceneManager.LoadScene(1);

    }

    private void resetData() {
        Time.timeScale = 1;
        GameConfig.landmineDamage = this.originLandmineDamage;
        GameConfig.laserDamage = this.originLaserDamage;
        GameConfig.bossEnemyHP = this.originBossEnemyHP;
        GameConfig.mainTankHP = this.originLifes;
        GameConfig.destoryEnemyDamage = 0f;
        GameConfig.goldCoin = 0;
        GameConfig.destoryEnemyNum = 0;
    }


}
