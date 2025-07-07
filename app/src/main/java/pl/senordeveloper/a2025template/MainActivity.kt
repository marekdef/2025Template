package pl.senordeveloper.a2025template

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import pl.senordeveloper.a2025template.A11yShowcaseViewModel.A11yShowcaseEvent.DisplaySnackBar
import pl.senordeveloper.a2025template.A11yShowcaseViewModel.A11yShowcaseEvent.OpenWebBrowser
import pl.senordeveloper.a2025template.ui.A11yShowcase
import pl.senordeveloper.a2025template.ui.ObserveAsEvents
import pl.senordeveloper.a2025template.ui.theme._2025TemplateTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<A11yShowcaseViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val a11yShowcaseState = viewModel.a11yShowcaseStateFlow.collectAsStateWithLifecycle()
            val scope = rememberCoroutineScope()
            val snackbarHostState = remember { SnackbarHostState() }

            _2025TemplateTheme {
                ObserveAsEvents(
                    flow = viewModel.events
                ) { event ->
                    when (event) {
                        is DisplaySnackBar -> {
                            scope.launch {
                                snackbarHostState.currentSnackbarData?.dismiss()
                                snackbarHostState.showSnackbar(
                                    message = event.message,
                                    duration = SnackbarDuration.Short
                                )
                            }
                        }

                        is OpenWebBrowser -> {
                            val intent = Intent(Intent.ACTION_VIEW, event.url.toUri())
                            runCatching {
                                if (intent.resolveActivity(packageManager) != null) {
                                    startActivity(intent)
                                }
                            }
                        }
                    }
                }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    },
                ) { innerPadding ->
                    A11yShowcase(
                        modifier = Modifier.padding(innerPadding),
                        a11yShowcaseState = a11yShowcaseState.value
                    )
                }
            }
        }
    }
}
