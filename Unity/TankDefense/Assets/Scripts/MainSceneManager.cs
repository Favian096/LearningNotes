using System.Collections;
using TMPro;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class MainSceneManager : MonoBehaviour {

    public Button startButton;
    public AudioSource audioSource;
    public Button exitButton;
    public GameObject loadPanel;
    public Slider slider;
    public TextMeshProUGUI text;
    private bool isLoading;

    void Start() {
        this.isLoading = false;
        this.loadPanel.SetActive(false);
    }

    void Update() {
        if (this.isLoading) {

        }
    }

    // 开始按钮监听
    public void onStartButtonClick() {
        this.loadPanel.SetActive(true);
        this.audioSource.Play();
        StartCoroutine(this.loadScene());
    }


    private IEnumerator loadScene() {
        AsyncOperation operation = SceneManager.LoadSceneAsync(1);

        operation.allowSceneActivation = false;

        while (!operation.isDone) {
            this.slider.value = operation.progress;
            this.text.text = "Loading... " + operation.progress * 100 + "%";

            if (operation.progress >= 0.9f) {
                slider.value = 1;
                this.text.text = "Press any key to continue";
                this.text.fontSize = 14;
                if (Input.anyKeyDown) {
                    operation.allowSceneActivation = true;
                }
            }

            yield return null;
        }
    }


    //退出按钮监听
    public void onExitButtonClick() {
        this.audioSource.Play();
#if UNITY_EDITOR
        UnityEditor.EditorApplication.isPlaying = false;
#else
    Application.Quit();
#endif
        this.exitButton.interactable = false;

    }
}
