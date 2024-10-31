package com.example.bottomsheetprismavi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bottomsheetprismavi.ui.theme.BottomSheetPrismaVITheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BottomSheetPrismaVITheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    fun parseColor(colorString: String): Color {
                        val colorLong = colorString.removePrefix("#").toLong(16) // Remove '#' e converte para Long
                        return Color(colorLong or 0xFF000000) // Adiciona opacidade caso não esteja presente
                    }

                    val sheetState = rememberModalBottomSheetState()
                    var isSheetOpen by remember { mutableStateOf(false) } //Variável de estado para controlar se o BottomSheet está aberto ou fechado
                    val scaffoldState = rememberBottomSheetScaffoldState()
                    val scope = rememberCoroutineScope()

                    val colorName: String = "Color Name"
                    val colorTemperatureName: String = "HOT"
                    val hexCode: String = "FFFFFF"
                    val formatedHexCode: Color = parseColor(hexCode)
                    val colorHex: String = "HEX: #$hexCode"
                    val colorRgb: String = "RGB: (0, 0, 0)"
                    val colorRyb: String = "RYB: (0%, 0%, 0%)"
                    val descriptionText: String = "The color HOT embodies an intense and vibrant hue that evokes feelings of warmth and energy. It radiates a bold, fiery essence, reminiscent of a blazing sunset or glowing embers. This captivating shade captures attention and ignites passion, making it perfect for designs that aim to inspire excitement and vitality.\""
                    val colorTerminology: String = "Primary"
                    val colorMatch1: String = "#000000"
                    val colorMatch2: String = "#111111"

                    // Exibir o BottomSheet parcialmente expandido ao iniciar o app
                    LaunchedEffect(Unit) {
                        scaffoldState.bottomSheetState.partialExpand()
                    }

                    BottomSheetScaffold(
                        scaffoldState = scaffoldState,
                        sheetPeekHeight = 200.dp, // Altura inicial do BottomSheet parcialmente expandido
                        sheetContent = {
                            // Conteúdo do BottomSheet
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Black.copy(0.3f))
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                // Coloque aqui o conteúdo do seu BottomSheet (Text, Row, etc.)
                                //Linha com Imagem e Textos
                                Row ( modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp), //Adicionando um prreenchimento interno acima
                                    verticalAlignment = Alignment.Top
                                ){

                                    Box(
                                        modifier = Modifier
                                            .size(150.dp)
                                            .clip(RoundedCornerShape(15.dp)) // Define a forma arredondada dos cantos
                                            .background(formatedHexCode) // Substitua 0xFF123456 pelo código hexadecimal desejado
                                            .padding(10.dp) // Espaçamento interno
                                    )

                                    // Coluna principal contendo o título e as informações
                                    Column(
                                        modifier = Modifier
                                            .padding(start = 15.dp) // Espaçamento entre a imagem e a coluna de textos
                                            .alignByBaseline()
                                            .fillMaxWidth() // Define a largura da coluna completa
                                    ) {
                                        // "Color Name" como título acima da Column de informações
                                        Text(
                                            text = colorName,
                                            fontSize = 16.sp,
                                            modifier = Modifier
                                                .padding(bottom = 8.dp) // Espaçamento abaixo do título
                                        )

                                        Column(
                                            modifier = Modifier
                                                .padding(start = 0.dp) // Espaçamento entre a imagem e a coluna de textos
                                                .height(100.dp) // Altura da coluna de textos)
                                                .fillMaxWidth() // Faz a coluna ocupar toda a largura
                                                .clip(RoundedCornerShape(15.dp)) // Define a forma arredondada dos cantos
                                                .background(Color.Gray.copy(alpha = 0.3f))
                                                .padding(16.dp) // Espaçamento interno

                                        ) {

                                            Text(
                                                text = colorHex,
                                                fontSize = 14.sp // Tamanho padrão
                                            )
                                            Text(
                                                text = colorRgb,
                                                fontSize = 14.sp// Tamanho menor do texto
                                            )
                                            Text(
                                                text = colorRyb,
                                                fontSize = 14.sp // Tamanho menor do texto
                                            )
                                        }

                                    }
                                }

                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp) // Preenchimento interno acima da coluna
                                ) {
                                    // Linha contendo "Color Temperature" e "Color Match"
                                    Row(
                                        modifier = Modifier.fillMaxWidth(), // Faz a Row ocupar toda a largura
                                        verticalAlignment = Alignment.CenterVertically // Alinha os elementos verticalmente
                                    ) {
                                        // Coluna para "Color Temperature" e "Color Terminology"
                                        Column(
                                            modifier = Modifier
                                                .weight(0.9f) // Faz a coluna ocupar metade do espaço disponível
                                                .padding(end = 8.dp) // Espaçamento à direita
                                        ) {
                                            // Texto "Color Temperature"
                                            Text(
                                                text = "Color Temperature",
                                                fontSize = 16.sp,
                                                modifier = Modifier
                                                    .padding(bottom = 8.dp) // Espaçamento abaixo do título
                                            )

                                            // Box para "Color Temperature" (sua temperatura de cor)
                                            Box(
                                                modifier = Modifier
                                                    .height(70.dp) // Altura do Box
                                                    .fillMaxWidth()
                                                    .clip(RoundedCornerShape(15.dp)) // Define a forma arredondada dos cantos
                                                    .background(Color.Gray.copy(alpha = 0.3f)) // Aplica o background cinza
                                                    .padding(16.dp) // Espaçamento interno
                                            ) {
                                                Text(
                                                    text = colorTemperatureName,
                                                    fontSize = 16.sp,
                                                    modifier = Modifier.align(Alignment.CenterStart) // Alinha o texto à esquerda
                                                )
                                            }

                                            // Texto "Color Terminology"
                                            Text(
                                                text = "Color Terminology",
                                                fontSize = 16.sp,
                                                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp) // Espaçamento acima e abaixo do texto
                                            )

                                            // Box para "Color Terminology"
                                            Box(
                                                modifier = Modifier
                                                    .height(70.dp) // Altura do Box
                                                    .fillMaxWidth()
                                                    .clip(RoundedCornerShape(15.dp)) // Define a forma arredondada dos cantos
                                                    .background(Color.Gray.copy(alpha = 0.3f)) // Aplica o background cinza
                                                    .padding(16.dp) // Espaçamento interno
                                            ) {
                                                // Colocando o texto alinhado à esquerda e centralizado verticalmente
                                                Text(
                                                    text = colorTerminology,
                                                    fontSize = 16.sp, // Tamanho padrão
                                                    modifier = Modifier
                                                        .align(Alignment.CenterStart) // Alinha o texto à esquerda, mas centraliza verticalmente
                                                )
                                            }
                                        }

                                        // Coluna para "Color Match"
                                        Column(
                                            modifier = Modifier
                                                .weight(1f) // Faz a coluna ocupar metade do espaço disponível
                                                .height(210.dp)
                                                .padding(start = 10.dp) // Espaçamento à esquerda
                                        ) {
                                            // Texto "Color Match"
                                            Text(
                                                text = "Color Match",
                                                fontSize = 16.sp,
                                                modifier = Modifier
                                                    .padding(bottom = 8.dp) // Espaçamento abaixo do título
                                                    .align(Alignment.Start)
                                            )

                                            // Box para os detalhes do "Color Match"
                                            Box(
                                                modifier = Modifier
                                                    .height(250.dp) // Aumente a altura se necessário
                                                    .clip(RoundedCornerShape(15.dp)) // Define a forma arredondada dos cantos
                                                    .background(Color.Yellow.copy(alpha = 0.1f)) // Aplica o background cinza
                                                    .padding(10.dp) // Espaçamento interno
                                            ) {
                                                // Usando uma Column para organizar cada par de imagem e texto
                                                Column(
                                                    modifier = Modifier.fillMaxWidth() // Preenche a largura do Box
                                                        .padding(10.dp)
                                                        .fillMaxHeight(),
                                                    verticalArrangement = Arrangement.Center
                                                ) {
                                                    Row(
                                                        modifier = Modifier.fillMaxWidth(),
                                                        verticalAlignment = Alignment.CenterVertically // Alinha verticalmente ao centro
                                                    ) {
                                                        // Imagem para o primeiro colorMatch
                                                        Image(
                                                            painter = painterResource(id = R.drawable.colorimage), // Substitua "image1" pelo nome da sua imagem
                                                            contentDescription = null,
                                                            modifier = Modifier.size(60.dp) // Tamanho da imagem
                                                                .padding(top = 5.dp)
                                                        )

                                                        // Texto para o primeiro colorMatch
                                                        Text(
                                                            text = colorMatch1, // Título do Box
                                                            fontSize = 14.sp,
                                                            modifier = Modifier
                                                                .padding(start = 4.dp) // Espaçamento à esquerda do texto
                                                        )
                                                    }

                                                    Spacer(modifier = Modifier.height(8.dp))

                                                    Row(
                                                        modifier = Modifier.fillMaxWidth(),
                                                        verticalAlignment = Alignment.CenterVertically // Alinha verticalmente ao centro
                                                    ) {
                                                        // Imagem para o segundo colorMatch
                                                        Image(
                                                            painter = painterResource(id = R.drawable.colorimage), // Substitua "image2" pelo nome da sua imagem
                                                            contentDescription = null, // Descrição da imagem (opcional)
                                                            modifier = Modifier.size(60.dp) // Tamanho da imagem
                                                                .padding(top = 5.dp)
                                                        )

                                                        // Texto para o segundo colorMatch
                                                        Text(
                                                            text = colorMatch2, // Título do Box
                                                            fontSize = 14.sp,
                                                            modifier = Modifier
                                                                .padding(start = 4.dp) // Espaçamento à esquerda do texto
                                                        )
                                                    }
                                                }
                                            }
                                        }

                                    }
                                }

                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp), // Preenchimento interno acima da coluna
                                ) {
                                    // Texto acima da Column de detalhes
                                    Text(
                                        text = "Description",
                                        fontSize = 16.sp,
                                        modifier = Modifier.padding(bottom = 8.dp) // Espaçamento abaixo do texto
                                    )

                                    Box(
                                        modifier = Modifier
                                            .height(260.dp) // Altura do Box
                                            .width(600.dp) // Largura do Box
                                            .clip(RoundedCornerShape(15.dp)) // Define a forma arredondada dos cantos
                                            .background(Color.Gray.copy(alpha = 0.3f)) // Aplica o background cinza
                                            .padding(16.dp) // Espaçamento interno
                                    ) {
                                        // Colocando o texto alinhado à esquerda e centralizado verticalmente
                                        Text(
                                            text = descriptionText,
                                            fontSize = 16.sp, // Tamanho padrão
                                            modifier = Modifier
                                                .align(Alignment.CenterStart) // Alinha o texto à esquerda, mas centraliza verticalmente
                                        )
                                    }
                                }






                            }
                        }
                    ) {
                        // Conteúdo principal da tela
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Button(onClick = {
                                scope.launch {
                                    scaffoldState.bottomSheetState.expand()
                                }
                            }) {
                                Text(text = "Open PrismaVI Bottom Sheet!")
                            }
                        }
                    }
                }
            }
        }
    }
}
