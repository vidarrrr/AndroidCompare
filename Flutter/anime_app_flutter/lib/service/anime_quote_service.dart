import 'package:retrofit/retrofit.dart';
import 'package:dio/dio.dart';

import '../model/anime_quote_model.dart';

part 'anime_quote_service.g.dart';

@RestApi(baseUrl: "https://animechan.vercel.app/")
abstract class RestClient {
  factory RestClient(Dio dio, {String baseUrl}) = _RestClient;

  @GET("api/quotes")
  Future<List<AnimeQuoteModel>> getAnimeQuoteList();
}

// @JsonSerializable()
// class AnimeQuoteModel {
//   String? anime;
//   String? character;
//   String? quote;

//   AnimeQuoteModel({this.anime, this.character, this.quote});

//   factory AnimeQuoteModel.fromJson(Map<String, dynamic> json) =>
//       _$AnimeQuoteModelFromJson(json);
//   Map<String, dynamic> toJson() => _$AnimeQuoteModelToJson(this);
// }
